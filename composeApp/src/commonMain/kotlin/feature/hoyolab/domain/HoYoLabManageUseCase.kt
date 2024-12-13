/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.repository.HoYoLabRepository
import kotlinx.coroutines.flow.Flow

class HoYoLabManageUseCase(
    private val repository: HoYoLabRepository, private val zzzCryptoImpl: ZzzCrypto
) {
    suspend fun requestUserGameRolesAndSave(
        region: String,
        lToken: String,
        ltUid: String
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

    suspend fun deleteAccountFromDB(uid: Int) = repository.deleteAccountFromDB(uid)

    private suspend fun encryptAndSaveToDatabase(
        region: String, regionName: String, lToken: String, ltUid: String, uid: String
    ) {
        repository.addAccountToDB(
            uid.toInt(),
            region,
            regionName,
            zzzCryptoImpl.encryptData(lToken),
            zzzCryptoImpl.encryptData(ltUid)
        )
    }
}