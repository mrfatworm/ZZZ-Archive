/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.feedback.data

interface GoogleDocRepository {
    suspend fun submitFeedbackForm(
        issueType: String,
        language: String,
        issueContent: String,
        nickname: String,
        appVersion: String,
        deviceName: String,
        operatingSystem: String
    ): Result<Unit>
}