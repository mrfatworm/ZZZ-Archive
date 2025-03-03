/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package network

import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.MyAgentListResponse
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import feature.hoyolab.model.UserGameRolesResponse
import feature.hoyolab.model.my_agent_detail.MyAgentDetailResponse
import feature.hoyolab.model.my_agent_detail.stubMyAgentDetailResponse
import feature.hoyolab.model.stubGameRecordResponse
import feature.hoyolab.model.stubMyAgentListResponse
import feature.hoyolab.model.stubPlayerDetailResponse
import feature.hoyolab.model.stubSignResponse
import feature.hoyolab.model.stubUserGameRolesResponse

class FakeHoYoLabHttp : HoYoLabHttp {
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

    override suspend fun requestGameRecord(
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): GameRecordResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubGameRecordResponse
    }

    override suspend fun requestSign(
        languageCode: String,
        lToken: String,
        ltUid: String
    ): SignResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubSignResponse
    }

    override suspend fun requestMyAgentList(
        languageCode: String,
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): MyAgentListResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubMyAgentListResponse
    }

    override suspend fun requestMyAgentDetail(
        languageCode: String,
        uid: Int,
        region: String,
        agentId: Int,
        lToken: String,
        ltUid: String
    ): MyAgentDetailResponse {
        if (isError) {
            throw Exception("Fake error")
        }
        return stubMyAgentDetailResponse
    }
}