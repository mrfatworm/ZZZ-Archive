/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.SettingsRepository

class ThemeUseCase(private val settingsRepository: SettingsRepository) {
    fun getIsDarkTheme(): Boolean {
        return settingsRepository.getIsDarkTheme()
    }

    fun setIsDarkTheme(isDark: Boolean) {
        settingsRepository.setIsDarkTheme(isDark)
    }
}