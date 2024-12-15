/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.HoYoLabAccountDao
import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.mapper.toPlayerAccountInfo
import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.PlayerBasicInfo
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import network.HoYoLabHttp

class HoYoLabRepositoryImpl(
    private val httpClient: HoYoLabHttp, private val hoYoLabAccountDao: HoYoLabAccountDao
) : HoYoLabRepository {
    override suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerBasicInfo>> {
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

    override suspend fun requestGameRecord(
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): Result<GameRecordResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestGameRecord(uid, region, lToken, ltUid)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun requestSign(
        languageCode: String,
        lToken: String,
        ltUid: String
    ): Result<SignResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestSign(languageCode, lToken, ltUid)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> =
        hoYoLabAccountDao.getAccountList()

    override suspend fun getAccountFromDB(uid: Int): Flow<HoYoLabAccountEntity?> =
        hoYoLabAccountDao.getAccount(uid)

    override suspend fun addAccountToDB(
        uid: Int,
        region: String,
        regionName: String,
        level: Int,
        nickName: String,
        profileUrl: String,
        cardUrl: String,
        lToken: ByteArray,
        ltUid: ByteArray,
        updatedAt: Long
    ) {
        hoYoLabAccountDao.insertAccount(
            HoYoLabAccountEntity(
                uid = uid,
                region = region,
                regionName = regionName,
                level = level,
                nickName = nickName,
                profileUrl = profileUrl,
                cardUrl = cardUrl,
                lToken = lToken,
                ltUid = ltUid,
                updatedAt = updatedAt
            )
        )
    }

    override suspend fun deleteAccountFromDB(uid: Int) = hoYoLabAccountDao.deleteAccount(uid)
}