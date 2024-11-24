/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import kotlinx.coroutines.withTimeout
import network.GoogleDocHttp

class GoogleDocRepositoryImpl(private val httpClient: GoogleDocHttp) : GoogleDocRepository {

    override suspend fun submitFeedbackForm(
        issueType: String,
        language: String,
        issueContent: String,
        nickname: String,
        appVersion: String,
        deviceName: String,
        operatingSystem: String
    ): Result<Unit> {
        return try {
            withTimeout(httpClient.timeout) {
                httpClient.submitFeedbackForm(
                    issueType,
                    language,
                    issueContent,
                    nickname,
                    appVersion,
                    deviceName,
                    operatingSystem
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}