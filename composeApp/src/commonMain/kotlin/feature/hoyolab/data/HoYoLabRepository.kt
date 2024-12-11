/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data

import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.PlayerDetailResponse

interface HoYoLabRepository {
    suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerAccountInfo>>

    suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<PlayerDetailResponse>
}