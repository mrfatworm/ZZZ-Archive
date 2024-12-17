/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

import kotlinx.coroutines.flow.Flow

interface PixivRepository {
    fun getZzzTopic(): Flow<List<RecentArticle>>
    suspend fun updateZzzTopic(zzzTag: String): Result<PixivTopicResponse>
}