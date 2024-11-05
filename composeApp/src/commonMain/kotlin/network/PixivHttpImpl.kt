/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import app.home.model.PixivZzzTopic
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class PixivHttpImpl(engine: HttpClientEngine) : PixivHttp {
    override val timeout = 5000L
    private val client = createPixivHttpClient(engine)

    override suspend fun requestZzzTopic(zzzTag: String): PixivZzzTopic = client.get {
        url {
            path("/ajax/search/artworks/$zzzTag")
        }
        parameter("word", zzzTag)
        parameter("mode", "safe")
        parameter("lang", "zh")
        contentType(ContentType.Application.Json)
    }.body()
}


