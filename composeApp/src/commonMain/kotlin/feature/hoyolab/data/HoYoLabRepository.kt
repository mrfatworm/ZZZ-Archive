/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data

import feature.hoyolab.data.database.HoYoLabAccountEntity
import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.PlayerDetailResponse
import kotlinx.coroutines.flow.Flow

interface HoYoLabRepository {
    suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerAccountInfo>>

    suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<PlayerDetailResponse>

    suspend fun addAccountToDatabase(
        uid: Int, region: String, regionName: String, lToken: ByteArray, ltUid: ByteArray
    )

    suspend fun fetchAccountFromDB(): Flow<List<HoYoLabAccountEntity>>
}