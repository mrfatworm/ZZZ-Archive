/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package network

import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.UserGameRolesResponse
import feature.hoyolab.model.stubPlayerDetailResponse
import feature.hoyolab.model.stubUserGameRolesResponse

class FakeHoYoLabHttp : HoYoLabHttp {
    override val defaultTimeout: Long = 5000L
    private var isError = false
    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): UserGameRolesResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubUserGameRolesResponse
    }

    override suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): PlayerDetailResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubPlayerDetailResponse
    }
}