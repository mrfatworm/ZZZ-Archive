/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.news.data.OfficialNewsResponse
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class OfficialWebHttpImpl(engine: HttpClientEngine) :
    OfficialWebHttp {
    private val client = createOfficialWebHttpClient(engine)

    override suspend fun requestNews(amount: Int, languagePath: String): OfficialNewsResponse =
        client.get {
            url {
                path("/content_v2_user/app/3e9196a4b9274bd7/getContentList")
            }
            contentType(ContentType.Application.Json)
            parameter("iPageSize", amount)
            parameter("iPage", 1)
            parameter("iChanId", 288)
            parameter("sLangKey", languagePath)
        }.body()
}


