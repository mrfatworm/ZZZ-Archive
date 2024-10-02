/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
    private val zzzJpTag100LikeId = "ゼンゼロ100users入り"

    override suspend fun requestZzzTopic(): PixivZzzTopic = client.get {
        url {
            path("/ajax/search/artworks/$zzzJpTag100LikeId")
        }
        parameter("word", zzzJpTag100LikeId)
        parameter("mode", "safe")
        parameter("lang", "zh")
        contentType(ContentType.Application.Json)
    }.body()
}


