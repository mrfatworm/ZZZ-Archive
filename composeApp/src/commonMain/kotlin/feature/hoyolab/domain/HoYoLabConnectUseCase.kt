/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.HoYoLabRepository
import feature.hoyolab.model.PlayerAccountInfo

class HoYoLabConnectUseCase(
    private val repository: HoYoLabRepository
) {
    suspend fun requestUserGameRoles(
        region: String,
        lToken: String,
        ltUid: String
    ): Result<List<PlayerAccountInfo>> =
        repository.requestUserGameRolesByLToken(region, lToken, ltUid)
}