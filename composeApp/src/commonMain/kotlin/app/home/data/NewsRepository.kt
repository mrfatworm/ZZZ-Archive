/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.data

import app.home.model.OfficialNewsResponse
import kotlinx.coroutines.flow.Flow
import utils.ZzzResult

interface NewsRepository {
    suspend fun getNews(amount: Int): ZzzResult<OfficialNewsResponse>
    fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<ZzzResult<OfficialNewsResponse>>
}