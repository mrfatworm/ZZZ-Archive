/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.PlayerBasicInfo
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import kotlinx.coroutines.flow.Flow

interface HoYoLabConfigRepository {
    suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerBasicInfo>>

    suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<PlayerDetailResponse>

    suspend fun requestGameRecord(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<GameRecordResponse>

    suspend fun requestSign(
        languageCode: String,
        lToken: String,
        ltUid: String
    ): Result<SignResponse>

    suspend fun getAllAccountsFromDB(): Flow<List<HoYoLabAccountEntity>>

    suspend fun getAccountFromDB(uid: Int): Flow<HoYoLabAccountEntity?>

    suspend fun addAccountToDB(
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
    )

    suspend fun deleteAccountFromDB(uid: Int)
}