/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import network.PixivHttp
import utils.ZzzResult

class PixivRepositoryImpl(private val httpClient: PixivHttp) : PixivRepository {
    override suspend fun getZzzTopic(): ZzzResult<PixivZzzTopic> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestZzzTopic()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

    override fun getZzzTopicPeriodically(perMinutes: Int): Flow<ZzzResult<PixivZzzTopic>> = flow {
        while (true) {
            emit(getZzzTopic())
            delay(perMinutes * 60 * 1000L)
        }
    }.flowOn(Dispatchers.IO)
}