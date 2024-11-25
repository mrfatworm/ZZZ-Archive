/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.GoogleDocRepository

class GoogleDocUseCase(private val repository: GoogleDocRepository) {
    suspend fun submitFeedbackForm(
        issueType: String,
        language: String,
        issueContent: String,
        nickname: String,
        appVersion: String,
        deviceName: String,
        operatingSystem: String
    ): Result<Unit> {
        return repository.submitFeedbackForm(
            issueType, language, issueContent, nickname, appVersion, deviceName, operatingSystem
        )
    }
}