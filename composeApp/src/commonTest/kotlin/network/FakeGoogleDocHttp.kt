/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

class FakeGoogleDocHttp : GoogleDocHttp {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun submitFeedbackForm(
        issueType: String,
        language: String,
        issueContent: String,
        nickname: String,
        appVersion: String,
        deviceName: String,
        operatingSystem: String
    ) {
        return if (isError) {
            throw Exception()
        } else {
            Unit
        }
    }
}