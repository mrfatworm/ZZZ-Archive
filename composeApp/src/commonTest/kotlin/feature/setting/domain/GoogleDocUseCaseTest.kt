/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakeGoogleDocRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GoogleDocUseCaseTest {
    private val googleDocRepository = FakeGoogleDocRepository()
    private val googleDocUseCase = GoogleDocUseCase(googleDocRepository)

    @Test
    fun `Submit Feedback Form`() = runTest {
        val result = googleDocUseCase.submitFeedbackForm("", "", "", "", "", "", "").getOrNull()
        assertEquals(Unit, result)
    }

    @Test
    fun `Submit Feedback Form Failed`() = runTest {
        googleDocRepository.setError(true)
        val result = googleDocUseCase.submitFeedbackForm("", "", "", "", "", "", "").getOrNull()
        assertNull(result)
    }
}