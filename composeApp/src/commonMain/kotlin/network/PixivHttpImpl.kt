/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.pixiv.data.PixivTopicResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.path
import kotlinx.serialization.json.Json

class PixivHttpImpl(engine: HttpClientEngine) : PixivHttp {
    override val timeout = 5000L
    private val client = createPixivHttpClient(engine)

    override suspend fun requestZzzTopic(zzzTag: String): PixivTopicResponse {
        val response = client.get {
            url {
                path("/ajax/search/artworks/$zzzTag")
            }
            parameter("word", zzzTag)
            parameter("mode", "safe")
            parameter("lang", "zh")
        }.bodyAsText()
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }
}


