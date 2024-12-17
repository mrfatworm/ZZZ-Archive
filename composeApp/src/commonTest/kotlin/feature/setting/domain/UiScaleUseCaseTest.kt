/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakePreferenceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UiScaleUseCaseTest {

    private val repository = FakePreferenceRepository()
    private val useCase = UiScaleUseCase(repository)

    @Test
    fun `Get UI scale`() = runTest {
        val result = useCase.getUiScale().first()
        assertEquals(1f, result)
    }

    @Test
    fun `Set UI scale`() = runTest {
        useCase.setUiScale(2f)
        val result = useCase.getUiScale().first()
        assertEquals(2f, result)
    }

    @Test
    fun `Get Font scale`() = runTest {
        val result = useCase.getFontScale().first()
        assertEquals(1f, result)
    }

    @Test
    fun `Set Font scale`() = runTest {
        useCase.setFontScale(2f)
        val result = useCase.getFontScale().first()
        assertEquals(2f, result)
    }
}