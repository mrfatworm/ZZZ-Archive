/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.pixiv.data.PixivTopicResponse
import feature.pixiv.data.stubPixivTopicResponse

class FakePixivHttp : PixivHttp {
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