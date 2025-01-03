/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.feedback.data


class FakeGoogleDocRepository : GoogleDocRepository {
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
    ): Result<Unit> {
        return if (isError) {
            Result.failure(Exception("Fake Error"))
        } else {
            Result.success(Unit)
        }
    }
}