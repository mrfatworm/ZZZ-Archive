/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.domain

import feature.feedback.data.FakeGoogleDocRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GoogleDocUseCaseTest {
    private val googleDocRepository = FakeGoogleDocRepository()
    private val googleDocUseCase = GoogleDocUseCase(googleDocRepository)

    @Test
    fun `Submit feedback form`() = runTest {
        val result = googleDocUseCase.submitFeedbackForm("", "", "", "", "", "", "").getOrNull()
        assertEquals(result, Unit)
    }

    @Test
    fun `Submit feedback form error`() = runTest {
        googleDocRepository.setError(true)
        val result = googleDocUseCase.submitFeedbackForm("", "", "", "", "", "", "").getOrNull()
        assertNull(result)
    }
}