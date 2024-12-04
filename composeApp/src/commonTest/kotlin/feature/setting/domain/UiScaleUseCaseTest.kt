/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakePreferenceRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class UiScaleUseCaseTest {

    private val repository = FakePreferenceRepository()
    private val useCase = UiScaleUseCase(repository)

    @Test
    fun `Get UI scale`() {
        val result = useCase.getUiScale()
        assertEquals(result, 1f)
    }

    @Test
    fun `Set UI scale`() {
        useCase.setUiScale(2f)
        val result = useCase.getUiScale()
        assertEquals(result, 2f)
    }

    @Test
    fun `Get Font scale`() {
        val result = useCase.getFontScale()
        assertEquals(result, 1f)
    }

    @Test
    fun `Set Font scale`() {
        useCase.setFontScale(2f)
        val result = useCase.getFontScale()
        assertEquals(result, 2f)
    }
}