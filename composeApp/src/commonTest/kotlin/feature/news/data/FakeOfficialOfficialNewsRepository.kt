/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import utils.ZzzResult

class FakeOfficialOfficialNewsRepository : OfficialNewsRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getNews(amount: Int): ZzzResult<List<OfficialNewsListItem>> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(listOf(stubNewsListItem))
        }
    }
}