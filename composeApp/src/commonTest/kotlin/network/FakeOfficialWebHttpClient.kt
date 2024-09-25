/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.home.model.OfficialNewsResponse
import app.home.model.stubOfficialNewsDataResponse

class FakeOfficialWebHttpClient : OfficialWebHttpClient {
    override val timeout = 5000L
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestNews(amount: Int, langKey: String): OfficialNewsResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubOfficialNewsDataResponse
        }
    }
}