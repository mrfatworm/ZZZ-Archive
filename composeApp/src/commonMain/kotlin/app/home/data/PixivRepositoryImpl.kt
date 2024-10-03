/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import kotlinx.coroutines.withTimeout
import network.PixivHttp
import utils.ZzzResult

class PixivRepositoryImpl(private val httpClient: PixivHttp) : PixivRepository {
    override suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivZzzTopic> {
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