/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.repository.HoYoLabRepository
import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HoYoLabManageUseCase(
    private val repository: HoYoLabRepository,
    private val zzzCryptoImpl: ZzzCrypto,
    private val preferencesRepository: PreferencesRepository
) {
    suspend fun requestUserGameRolesAndSave(
        region: String, lToken: String, ltUid: String
    ): Result<Unit> {
        val result = repository.requestUserGameRolesByLToken(region, lToken, ltUid)
        result.fold(onSuccess = { accountInfo ->
            if (accountInfo.isEmpty()) {
                return Result.failure(Exception("No account found"))
            } else {
                encryptAndSaveToDatabase(
                    uid = accountInfo.first().uid,
                    region = region,
                    regionName = accountInfo.first().regionName,
                    lToken = lToken,
                    ltUid = ltUid,
                )
                return Result.success(Unit)
            }
        }, onFailure = {
            return Result.failure(it)
        })
    }

    suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> =
        repository.getAllAccountsFromDB()


    private suspend fun encryptAndSaveToDatabase(
        region: String, regionName: String, lToken: String, ltUid: String, uid: String
    ) {
        setDefaultAccountIfFirstAccount(uid)
        val currentTime = Clock.System.now().toEpochMilliseconds()
        val encryptLToken = zzzCryptoImpl.encryptData(lToken)
        val encryptLtUid = zzzCryptoImpl.encryptData(ltUid)
        repository.addAccountToDB(
            uid.toInt(), region, regionName, encryptLToken, encryptLtUid, currentTime
        )
    }

    private suspend fun setDefaultAccountIfFirstAccount(uid: String) {
        if (repository.getAllAccountsFromDB().firstOrNull()?.isEmpty() == true) {
            preferencesRepository.setDefaultHoYoLabAccountUid(uid.toInt())
        }
    }


    suspend fun deleteAccountFromDB(uid: Int) {
        repository.deleteAccountFromDB(uid)
        resetDefaultIfDeletedDefaultAccount(uid)
    }


    private suspend fun resetDefaultIfDeletedDefaultAccount(uid: Int) {
        if (preferencesRepository.getDefaultHoYoLabAccountUid() == uid) {
            preferencesRepository.setDefaultHoYoLabAccountUid(
                repository.getAllAccountsFromDB().firstOrNull()?.firstOrNull()?.uid ?: 0
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