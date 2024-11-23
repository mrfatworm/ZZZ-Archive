/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.domain

import feature.news.data.OfficialNewsListItem
import feature.news.data.stubNewsListItem
import feature.news.presentation.OfficialNewsState
import feature.news.presentation.stubOfficialNewsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.ZzzResult

class FakeOfficialNewsUseCase : OfficialNewsUseCase {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<ZzzResult<List<OfficialNewsListItem>>> {
        return if (isError) {
            flowOf(ZzzResult.Error(Exception()))
        } else {
            flowOf(ZzzResult.Success(listOf(stubNewsListItem)))
        }
    }

    override fun convertToOfficialNewsState(newsList: List<OfficialNewsListItem>): List<OfficialNewsState> {
        return listOf(stubOfficialNewsState)
    }
}