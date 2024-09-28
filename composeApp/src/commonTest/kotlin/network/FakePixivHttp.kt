/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.home.model.PixivZzzTopic
import app.home.model.stubPixivZzzTopic

class FakePixivHttp : PixivHttp {
    override val timeout = 5000L
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestZzzTopic(): PixivZzzTopic {
        return if (isError) {
            throw Exception()
        } else {
            stubPixivZzzTopic
        }
    }
}