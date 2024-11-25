/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.news.data.OfficialNewsResponse
import feature.setting.domain.LanguageUseCase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.path
import kotlinx.serialization.json.Json

class OfficialWebHttpImpl(engine: HttpClientEngine, languageUseCase: LanguageUseCase) :
    OfficialWebHttp {
    override val timeout = 5000L
    override val languagePath = languageUseCase.getLanguage().officialNewsCode
    private val client = createOfficialWebHttpClient(engine)

    override suspend fun requestNews(amount: Int): OfficialNewsResponse {
        val response = client.get {
            url {
                path("/content_v2_user/app/3e9196a4b9274bd7/getContentList")
            }
            parameter("iPageSize", amount)
            parameter("iPage", 1)
            parameter("iChanId", 288)
            parameter("sLangKey", languagePath)
        }.bodyAsText()

        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }

}


