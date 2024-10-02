/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import app.home.model.stubPixivZzzTopic
import utils.ZzzResult

class FakePixivRepository : PixivRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivZzzTopic> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubPixivZzzTopic)
        }
    }
}