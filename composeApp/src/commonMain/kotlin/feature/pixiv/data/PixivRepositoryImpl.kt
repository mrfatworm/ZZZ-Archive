/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

import kotlinx.coroutines.withTimeout
import network.PixivHttp
import utils.ZzzResult

class PixivRepositoryImpl(private val httpClient: PixivHttp) : PixivRepository {
    override suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivTopicResponse> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestZzzTopic(zzzTag)
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}