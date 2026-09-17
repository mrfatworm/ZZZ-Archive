/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.credential.HoYoLabCredential
import feature.hoyolab.data.credential.HoYoLabCredentialStore
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.repository.HoYoLabConfigRepository
import feature.setting.data.PreferencesRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime

class HoYoLabManageUseCase(
    private val hoYoLabConfigRepository: HoYoLabConfigRepository,
    private val credentialStore: HoYoLabCredentialStore,
    private val preferencesRepository: PreferencesRepository
) {
    suspend fun requestUserInfoAndSave(
        region: String,
        lToken: String,
        ltUid: String
    ): Result<Unit> {
        val result = hoYoLabConfigRepository.requestUserGameRolesByLToken(
            region = region,
            lToken = lToken,
            ltUid = ltUid
        )
        result.fold(onSuccess = { accountInfo ->
            if (accountInfo.isEmpty()) {
                return Result.failure(Exception("No account found"))
            } else {
                val playerDetailResult =
                    hoYoLabConfigRepository.requestPlayerDetail(
                        accountInfo.first().uid.toInt(),
                        region,
                        lToken,
                        ltUid
                    )
                playerDetailResult.fold(onSuccess = { playerDetail ->
                    saveAccount(
                        accountInfo.first().uid,
                        region,
                        accountInfo.first().regionName,
                        accountInfo.first().level,
                        accountInfo.first().nickname,
                        playerDetail.data.curHeadIconUrl,
                        playerDetail.data.gameDataShow.cardUrl,
                        lToken,
                        ltUid
                    )
                    return Result.success(Unit)
                }, onFailure = {
                    return Result.failure(it)
                })
            }
        }, onFailure = {
            return Result.failure(it)
        })
    }

    /**
     * The credential goes to the platform key store first: an orphaned credential is harmless,
     * whereas an account row without one can never be synced.
     */
    @OptIn(ExperimentalTime::class)
    private suspend fun saveAccount(
        uid: String,
        region: String,
        regionName: String,
        level: Int,
        nickName: String,
        profileUrl: String,
        cardUrl: String,
        lToken: String,
        ltUid: String
    ) {
        setDefaultAccountIfFirstAccount(uid)
        val currentTime = Clock.System.now().toEpochMilliseconds()
        credentialStore.save(uid.toInt(), HoYoLabCredential(lToken = lToken, ltUid = ltUid))
        hoYoLabConfigRepository.addAccountToDB(
            uid.toInt(),
            region,
            regionName,
            level,
            nickName,
            profileUrl,
            cardUrl,
            currentTime
        )
    }

    suspend fun reSyncAccount(uid: Int): Result<Unit> {
        val account = hoYoLabConfigRepository.getAccountFromDB(uid).filterNotNull().first()
        val credential = credentialStore.read(uid)
            ?: return Result.failure(MissingHoYoLabCredentialException(uid))
        return requestUserInfoAndSave(account.region, credential.lToken, credential.ltUid)
    }

    suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> =
        hoYoLabConfigRepository.getAllAccountsFromDB()

    private suspend fun setDefaultAccountIfFirstAccount(uid: String) {
        if (hoYoLabConfigRepository.getAllAccountsFromDB().firstOrNull()?.isEmpty() == true) {
            preferencesRepository.setDefaultHoYoLabAccountUid(uid.toInt())
        }
    }

    suspend fun deleteAccountFromDB(uid: Int) {
        credentialStore.delete(uid)
        hoYoLabConfigRepository.deleteAccountFromDB(uid)
        resetDefaultIfDeletedDefaultAccount(uid)
    }

    private suspend fun resetDefaultIfDeletedDefaultAccount(uid: Int) {
        if (preferencesRepository.getDefaultHoYoLabAccountUid().first() == uid) {
            preferencesRepository.setDefaultHoYoLabAccountUid(
                hoYoLabConfigRepository
                    .getAllAccountsFromDB()
                    .firstOrNull()
                    ?.firstOrNull()
                    ?.uid
                    ?: 0
            )
        }
    }

    @OptIn(ExperimentalTime::class)
    fun convertToLocalDatetime(
        timestamp: Long,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val datetimeInSystemZone: LocalDateTime = instant.toLocalDateTime(timeZone)
        return datetimeInSystemZone.run {
            "$year-${month.number}-$day $hour:$minute"
        }
    }
}
