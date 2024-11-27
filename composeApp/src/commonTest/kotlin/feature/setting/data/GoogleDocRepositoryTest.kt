/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.data

import kotlinx.coroutines.test.runTest
import network.FakeGoogleDocHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GoogleDocRepositoryTest {
    private val client = FakeGoogleDocHttp()
    private val repository = GoogleDocRepositoryImpl(client)

    @Test
    fun `Submit feedback form success`() = runTest {
        val result = repository.submitFeedbackForm(
            "issueType",
            "language",
            "issueContent",
            "nickname",
            "appVersion",
            "deviceName",
            "operatingSystem"
        ).getOrNull()
        assertEquals(result, Unit)
    }

    @Test
    fun `Submit feedback form error`() = runTest {
        client.setError(true)
        val result = repository.submitFeedbackForm(
            "issueType",
            "language",
            "issueContent",
            "nickname",
            "appVersion",
            "deviceName",
            "operatingSystem"
        ).getOrNull()
        assertNull(result)
    }
}