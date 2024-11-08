/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.data

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
    ): Boolean {
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
            true
        } catch (e: Exception) {
            println("Send Google Form Failed: $e")
            false
        }
    }
}