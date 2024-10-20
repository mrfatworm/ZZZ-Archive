/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.OfficialNewsResponse
import app.home.model.stubOfficialNewsDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.ZzzResult

class FakeNewsRepository : NewsRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getNews(amount: Int): ZzzResult<OfficialNewsResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubOfficialNewsDataResponse)
        }
    }

    override fun getNewsPeriodically(
        perMinutes: Int,
        amount: Int
    ): Flow<ZzzResult<OfficialNewsResponse>> {
        return if (isError) {
            flowOf(ZzzResult.Error(Exception()))
        } else {
            flowOf(ZzzResult.Success(stubOfficialNewsDataResponse))
        }
    }
}