/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.OfficialNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import network.OfficialWebHttpClient
import utils.ZzzResult

class NewsRepositoryImpl(private val httpClient: OfficialWebHttpClient) : NewsRepository {
    override suspend fun getNews(amount: Int, langKey: String): ZzzResult<OfficialNewsResponse> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestNews(amount, langKey)
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

    override fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int,
        langKey: String
    ): Flow<ZzzResult<OfficialNewsResponse>> = flow {
        while (true) {
            emit(getNews(amount, langKey))
            delay(perMinutes * 60 * 1000L)
        }
    }.flowOn(Dispatchers.IO)
}