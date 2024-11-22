/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.home.model.response.PixivTopicResponse
import feature.home.model.response.stubPixivTopicResponse

class FakePixivHttp : PixivHttp {
    override val timeout = 5000L
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestZzzTopic(zzzTag: String): PixivTopicResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubPixivTopicResponse
        }
    }
}