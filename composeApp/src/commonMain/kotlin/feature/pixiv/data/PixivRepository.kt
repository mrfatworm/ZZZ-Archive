/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

interface PixivRepository {
    suspend fun getZzzTopic(zzzTag: String): Result<PixivTopicResponse>
}