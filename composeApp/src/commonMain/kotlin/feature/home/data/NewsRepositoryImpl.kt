/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.OfficialNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import network.OfficialWebHttp
import utils.ZzzResult

class NewsRepositoryImpl(private val httpClient: OfficialWebHttp) : NewsRepository {
    override suspend fun getNews(amount: Int): ZzzResult<OfficialNewsResponse> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestNews(amount)
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

    override fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<ZzzResult<OfficialNewsResponse>> = flow {
        while (true) {
            emit(getNews(amount))
            delay(perMinutes * 60 * 1000L)
        }
    }.flowOn(Dispatchers.IO)
}