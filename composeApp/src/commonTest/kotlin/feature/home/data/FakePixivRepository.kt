/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.PixivZzzTopic
import feature.home.model.stubPixivZzzTopic
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