/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.domain

import feature.pixiv.data.PixivRepository
import feature.pixiv.data.PixivTopicResponse
import feature.pixiv.data.RecentArticle
import kotlinx.coroutines.flow.Flow

class PixivUseCase(private val repository: PixivRepository) {
    fun invoke(): Flow<List<RecentArticle>> = repository.getZzzTopic()
    suspend fun updateZzzTopic(zzzTag: String): Result<PixivTopicResponse> =
        repository.updateZzzTopic(zzzTag)
}