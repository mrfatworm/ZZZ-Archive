/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.hoyolab.model.GameRecordResponse
import feature.hoyolab.model.PlayerDetailResponse
import feature.hoyolab.model.SignResponse
import feature.hoyolab.model.UserGameRolesResponse
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom

class HoYoLabHttpImpl(engine: HttpClientEngine) : HoYoLabHttp {
    private val client = createHoYoLabHttpClient(engine)

    override suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): UserGameRolesResponse = client.get {
        url {
            takeFrom("https://api-account-os.hoyolab.com/binding/api/getUserGameRolesByLtoken")
        }
        parameter("game_biz", "nap_global")
        parameter("region", region)
        cookie("ltoken_v2", lToken)
        cookie("ltuid_v2", ltUid)
        contentType(ContentType.Application.Json)
    }.body()

    override suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): PlayerDetailResponse = client.get {
        url {
            takeFrom("https://sg-public-api.hoyolab.com/event/game_record_zzz/api/zzz/index")
        }
        parameter("server", region)
        parameter("role_id", uid)
        cookie("ltoken_v2", lToken)
        cookie("ltuid_v2", ltUid)
        contentType(ContentType.Application.Json)
    }.body()

    override suspend fun requestGameRecord(
        uid: Int, region: String, lToken: String, ltUid: String
    ): GameRecordResponse = client.get {
        url {
            takeFrom("https://sg-public-api.hoyolab.com/event/game_record_zzz/api/zzz/note")
        }
        parameter("server", region)
        parameter("role_id", uid)
        cookie("ltoken_v2", lToken)
        cookie("ltuid_v2", ltUid)
        contentType(ContentType.Application.Json)
    }.body()

    override suspend fun requestSign(
        languageCode: String,
        lToken: String,
        ltUid: String
    ): SignResponse = client.post {
        url {
            takeFrom(" https://sg-public-api.hoyolab.com/event/luna/zzz/os/sign")
        }
        parameter("lang", languageCode)
        parameter("act_id", "e202406031448091")
        cookie("ltoken_v2", lToken)
        cookie("ltuid_v2", ltUid)
        header("x-rpc-signgame", "zzz")
        contentType(ContentType.Application.Json)
    }.body()
}


