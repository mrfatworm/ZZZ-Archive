/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePixivRepository : PixivRepository {
    private var isError = false
    private val _pixivArticleList = MutableStateFlow(stubPixivTopicResponse.body.illustManga.data)
    override fun getZzzTopic(): Flow<List<RecentArticle>> = _pixivArticleList

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun updateZzzTopic(zzzTag: String): Result<PixivTopicResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubPixivTopicResponse)
        }
    }
}