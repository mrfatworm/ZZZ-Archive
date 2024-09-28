/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.home.model.PixivZzzTopic
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class PixivHttpImpl(engine: HttpClientEngine) : PixivHttp {
    override val timeout = 5000L
    private val client = createPixivHttpClient(engine)
    private val zzzZhTagId = "绝区零"
    private val zzzJpTagId = "ゼンレスゾーンゼロ"
    private val zzzEnTagId = "ZenlessZoneZero"

    override suspend fun requestZzzTopic(): PixivZzzTopic = client.get {
        url {
            path("/ajax/search/top/$zzzJpTagId")
        }

        contentType(ContentType.Application.Json)
    }.body()
}


