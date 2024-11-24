/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.data

import utils.ZzzResult

class FakePixivRepository : PixivRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivTopicResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubPixivTopicResponse)
        }
    }
}