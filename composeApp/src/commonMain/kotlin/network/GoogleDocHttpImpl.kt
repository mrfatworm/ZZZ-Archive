/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class GoogleDocHttpImpl(engine: HttpClientEngine) : GoogleDocHttp {
    override val timeout = 5000L
    private val client = createGoogleDocHttpClient(engine)

    override suspend fun submitFeedbackForm(
        issueType: String,
        language: String,
        issueContent: String,
        nickname: String,
        appVersion: String,
        deviceName: String,
        operatingSystem: String
    ) {
        client.post {
            url {
                path("/forms/u/0/d/e/1FAIpQLSfADuDlhoYOfePHQWPEQlBGPJLzMgG8fN3sUwN3t8qQfY3tKg/formResponse")
            }
            parameter("pli", 1)
            parameter("entry.484308305", issueType)
            parameter("entry.1106620463", language)
            parameter("entry.1327077448", issueContent)
            parameter("entry.1637450330", nickname)
            parameter("entry.58201369", appVersion)
            parameter("entry.1615440715", deviceName)
            parameter("entry.1501925155", operatingSystem)
            contentType(ContentType.Application.Json)
        }
    }
}


