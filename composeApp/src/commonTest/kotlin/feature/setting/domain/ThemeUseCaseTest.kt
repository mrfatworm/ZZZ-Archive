/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakePreferenceRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class ThemeUseCaseTest {
    private val settingsRepository = FakePreferenceRepository()
    private val themeUseCase = ThemeUseCase(settingsRepository)

    @Test
    fun `Set dark theme`() {
        themeUseCase.setPreferenceIsDarkTheme(true)
        assertTrue(settingsRepository.getIsDarkTheme())
    }
}