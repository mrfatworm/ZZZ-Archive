/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withTimeout
import network.PixivHttp

class PixivRepositoryImpl(private val httpClient: PixivHttp) : PixivRepository {
    private val _pixivArticleList = MutableStateFlow<List<RecentArticle>>(emptyList())
    override fun getZzzTopic(): Flow<List<RecentArticle>> = _pixivArticleList

    override suspend fun updateZzzTopic(zzzTag: String): Result<PixivTopicResponse> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestZzzTopic(zzzTag)
            }
            _pixivArticleList.value = result.body.illustManga.data
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}