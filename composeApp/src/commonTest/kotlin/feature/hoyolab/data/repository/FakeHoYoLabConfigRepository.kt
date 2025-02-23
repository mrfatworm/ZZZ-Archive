/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.PlayerBasicInfo
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import feature.hoyolab.model.stubGameRecordResponse
import feature.hoyolab.model.stubPlayerBasicInfo
import feature.hoyolab.model.stubPlayerDetailResponse
import feature.hoyolab.model.stubSignResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHoYoLabConfigRepository : HoYoLabConfigRepository {
    private var isError = false
    private val accountListInDB = mutableListOf(stubHoYoLabAccountEntity)

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    fun clearAccountList() {
        accountListInDB.clear()
    }

    override suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>> = flow {
        emit(accountListInDB)
    }

    override suspend fun getAccountFromDB(uid: Int): Flow<HoYoLabAccountEntity?> = flow {
        emit(accountListInDB.find { it.uid == uid } ?: throw Exception("Account not found"))
    }

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
        accountListInDB.add(
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

    override suspend fun deleteAccountFromDB(uid: Int) {
        accountListInDB.removeAt(accountListInDB.indexOfFirst { it.uid == uid })
    }

    override suspend fun requestUserGameRolesByLToken(
        region: String,
        lToken: String,
        ltUid: String
    ): Result<List<PlayerBasicInfo>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(listOf(stubPlayerBasicInfo))
        }
    }

    override suspend fun requestPlayerDetail(
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): Result<PlayerDetailResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubPlayerDetailResponse)
        }
    }

    override suspend fun requestGameRecord(
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): Result<GameRecordResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubGameRecordResponse)
        }
    }

    override suspend fun requestSign(
        languageCode: String,
        lToken: String,
        ltUid: String
    ): Result<SignResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubSignResponse)
        }
    }
}