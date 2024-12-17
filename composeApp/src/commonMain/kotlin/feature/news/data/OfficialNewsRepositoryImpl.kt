/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import kotlinx.coroutines.withTimeout
import network.OfficialWebHttp

class OfficialNewsRepositoryImpl(private val httpClient: OfficialWebHttp) : OfficialNewsRepository {
    override suspend fun getNews(
        amount: Int,
        languagePath: String
    ): Result<List<OfficialNewsListItemResponse>> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestNews(amount, languagePath)
            }
            Result.success(result.data.list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}