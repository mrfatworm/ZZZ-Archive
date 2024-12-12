/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.HoYoLabRepository
import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.model.HoYoLabAccount
import feature.hoyolab.model.PlayerAccountInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HoYoLabManageUseCase(
    private val repository: HoYoLabRepository, private val zzzCryptoImpl: ZzzCrypto
) {
    suspend fun requestUserGameRoles(
        region: String,
        lToken: String,
        ltUid: String
    ): Result<List<PlayerAccountInfo>> =
        repository.requestUserGameRolesByLToken(region, lToken, ltUid)

    suspend fun getAccountFromDB(): Flow<List<HoYoLabAccount>> = flow {
        repository.fetchAccountFromDB().collect { accountList ->
            emit(accountList.map {
                HoYoLabAccount(
                    uid = it.uid,
                    region = it.region,
                    regionName = it.regionName,
                    lToken = zzzCryptoImpl.decryptData(it.lToken),
                    ltUid = zzzCryptoImpl.decryptData(it.ltUid)
                )
            })
        }
    }

    suspend fun encryptAndSaveToDatabase(
        region: String, regionName: String, lToken: String, ltUid: String, uid: String
    ) {
        repository.addAccountToDatabase(
            uid.toInt(),
            region,
            regionName,
            zzzCryptoImpl.encryptData(lToken),
            zzzCryptoImpl.encryptData(ltUid)
        )
    }
}