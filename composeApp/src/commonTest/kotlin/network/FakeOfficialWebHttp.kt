/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.news.data.OfficialNewsResponse
import feature.news.data.stubOfficialNewsDataResponse

class FakeOfficialWebHttp : OfficialWebHttp {
    override val timeout = 5000L
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestNews(amount: Int, languagePath: String): OfficialNewsResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubOfficialNewsDataResponse
        }
    }
}