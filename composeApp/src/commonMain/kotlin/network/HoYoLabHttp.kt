/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package network

import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import feature.hoyolab.model.UserGameRolesResponse

interface HoYoLabHttp {
    val defaultTimeout: Long
    suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): UserGameRolesResponse

    suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): PlayerDetailResponse

    suspend fun requestGameRecord(
        uid: Int, region: String, lToken: String, ltUid: String
    ): GameRecordResponse

    suspend fun requestSign(languageCode: String, lToken: String, ltUid: String): SignResponse
}