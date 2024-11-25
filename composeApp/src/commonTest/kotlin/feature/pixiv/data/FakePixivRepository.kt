/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.data

class FakePixivRepository : PixivRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getZzzTopic(zzzTag: String): Result<PixivTopicResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubPixivTopicResponse)
        }
    }
}