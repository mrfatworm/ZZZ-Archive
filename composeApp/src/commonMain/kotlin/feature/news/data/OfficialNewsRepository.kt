/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

interface OfficialNewsRepository {
    suspend fun getNews(amount: Int, languagePath: String): Result<List<OfficialNewsListItem>>
}