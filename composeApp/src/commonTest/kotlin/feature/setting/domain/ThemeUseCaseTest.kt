/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.FakeSettingRepository
import kotlin.test.Test
import kotlin.test.assertTrue

class ThemeUseCaseTest {
    private val settingsRepository = FakeSettingRepository()
    private val themeUseCase = ThemeUseCase(settingsRepository)

    @Test
    fun `Set Dark Theme`() {
        themeUseCase.setIsDarkTheme(true)
        assertTrue(settingsRepository.getIsDarkTheme())
    }
}