/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.HoYoLabAccountDao
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.mapper.toPlayerAccountInfo
import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.PlayerDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import network.HoYoLabHttp

class HoYoLabRepositoryImpl(
    private val httpClient: HoYoLabHttp, private val hoYoLabAccountDao: HoYoLabAccountDao
) : HoYoLabRepository {
    override suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerAccountInfo>> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestUserGameRolesByLToken(region, lToken, ltUid)
            }
            Result.success(result.data.list.map { it.toPlayerAccountInfo() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<PlayerDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestPlayerDetail(uid, region, lToken, ltUid)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> =
        hoYoLabAccountDao.getAccountList()

    override suspend fun getAccountFromDB(uid: Int): Flow<HoYoLabAccountEntity> =
        hoYoLabAccountDao.getAccount(uid)

    override suspend fun addAccountToDB(
        uid: Int, region: String, regionName: String, lToken: ByteArray, ltUid: ByteArray
    ) {
        hoYoLabAccountDao.insertAccount(
            HoYoLabAccountEntity(
                uid = uid,
                region = region,
                regionName = regionName,
                lToken = lToken,
                ltUid = ltUid,
            )
        )
    }

    override suspend fun deleteAccountFromDB(uid: Int) = hoYoLabAccountDao.deleteAccount(uid)
}