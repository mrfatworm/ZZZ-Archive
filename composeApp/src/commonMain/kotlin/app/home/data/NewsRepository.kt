/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.OfficialNewsResponse
import kotlinx.coroutines.flow.Flow
import utils.ZzzResult

interface NewsRepository {
    suspend fun getNews(amount: Int, langKey: String): ZzzResult<OfficialNewsResponse>
    fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int,
        langKey: String
    ): Flow<ZzzResult<OfficialNewsResponse>>
}