/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import utils.ZzzResult

interface OfficialNewsRepository {
    suspend fun getNews(amount: Int): ZzzResult<List<OfficialNewsListItem>>
}