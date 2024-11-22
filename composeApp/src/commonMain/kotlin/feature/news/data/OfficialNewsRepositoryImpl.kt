/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import kotlinx.coroutines.withTimeout
import network.OfficialWebHttp
import utils.ZzzResult

class OfficialNewsRepositoryImpl(private val httpClient: OfficialWebHttp) : OfficialNewsRepository {
    override suspend fun getNews(amount: Int): ZzzResult<List<OfficialNewsListItem>> {
        return try {
            val result = withTimeout(httpClient.timeout) {
                httpClient.requestNews(amount)
            }
            ZzzResult.Success(result.data.list)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

}