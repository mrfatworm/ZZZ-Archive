/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.repository.HoYoLabConfigRepository
import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HoYoLabManageUseCase(
    private val hoYoLabConfigRepository: HoYoLabConfigRepository,
    private val zzzCryptoImpl: ZzzCrypto,
    private val preferencesRepository: PreferencesRepository
) {
    suspend fun requestUserInfoAndSave(
        region: String, lToken: String, ltUid: String
    ): Result<Unit> {
        val result = hoYoLabConfigRepository.requestUserGameRolesByLToken(region, lToken, ltUid)
        result.fold(onSuccess = { accountInfo ->
            if (accountInfo.isEmpty()) {
                return Result.failure(Exception("No account found"))
            } else {
                val playerDetailResult = hoYoLabConfigRepository.requestPlayerDetail(
                    accountInfo.first().uid.toInt(),
                    region,
                    lToken,
                    ltUid
                )
                playerDetailResult.fold(onSuccess = { playerDetail ->
                    encryptAndSaveToDatabase(
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

    private suspend fun encryptAndSaveToDatabase(
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
        val encryptLToken = zzzCryptoImpl.encryptData(lToken)
        val encryptLtUid = zzzCryptoImpl.encryptData(ltUid)
        hoYoLabConfigRepository.addAccountToDB(
            uid.toInt(),
            region,
            regionName,
            level,
            nickName,
            profileUrl,
            cardUrl,
            encryptLToken,
            encryptLtUid,
            currentTime
        )
    }

    suspend fun reSyncAccount(uid: Int) {
        val account = hoYoLabConfigRepository.getAccountFromDB(uid).filterNotNull()
        val decryptedLToken = zzzCryptoImpl.decryptData(account.first().lToken)
        val decryptedLtUid = zzzCryptoImpl.decryptData(account.first().ltUid)
        requestUserInfoAndSave(account.first().region, decryptedLToken, decryptedLtUid)
    }

    suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> =
        hoYoLabConfigRepository.getAllAccountsFromDB()

    private suspend fun setDefaultAccountIfFirstAccount(uid: String) {
        if (hoYoLabConfigRepository.getAllAccountsFromDB().firstOrNull()?.isEmpty() == true) {
            preferencesRepository.setDefaultHoYoLabAccountUid(uid.toInt())
        }
    }


    suspend fun deleteAccountFromDB(uid: Int) {
        hoYoLabConfigRepository.deleteAccountFromDB(uid)
        resetDefaultIfDeletedDefaultAccount(uid)
    }


    private suspend fun resetDefaultIfDeletedDefaultAccount(uid: Int) {
        if (preferencesRepository.getDefaultHoYoLabAccountUid().first() == uid) {
            preferencesRepository.setDefaultHoYoLabAccountUid(
                hoYoLabConfigRepository.getAllAccountsFromDB().firstOrNull()?.firstOrNull()?.uid
                    ?: 0
            )
        }
    }

    fun convertToLocalDatetime(
        timestamp: Long, timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val datetimeInSystemZone: LocalDateTime = instant.toLocalDateTime(timeZone)
        return datetimeInSystemZone.run {
            "${year}-${monthNumber}-${dayOfMonth} ${hour}:${minute}"
        }
    }
}