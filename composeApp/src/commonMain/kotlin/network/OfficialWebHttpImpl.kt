/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.home.model.OfficialNewsResponse
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import utils.LanguageHandler

class OfficialWebHttpImpl(engine: HttpClientEngine, languageHandler: LanguageHandler) :
    OfficialWebHttp {
    override val timeout = 5000L
    override val languagePath = languageHandler.getLanguage().assetLang
    private val client = createOfficialWebHttpClient(engine)

    override suspend fun requestNews(amount: Int): OfficialNewsResponse =
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


