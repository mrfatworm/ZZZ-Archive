/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

class FakeOfficialNewsRepository : OfficialNewsRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getNews(
        amount: Int,
        languagePath: String
    ): Result<List<OfficialNewsListItemResponse>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(listOf(stubNewsListItem))
        }
    }
}