/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

import kotlinx.coroutines.withTimeout
import network.PixivHttp

class PixivRepositoryImpl(private val httpClient: PixivHttp) : PixivRepository {
    override suspend fun getZzzTopic(zzzTag: String): Result<PixivTopicResponse> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestZzzTopic(zzzTag)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}