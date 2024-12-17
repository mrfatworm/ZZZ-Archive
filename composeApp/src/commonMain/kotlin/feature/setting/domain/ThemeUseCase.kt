/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class ThemeUseCase(private val preferencesRepository: PreferencesRepository) {
    fun getPreferenceIsDarkTheme(): Flow<Boolean> = preferencesRepository.getIsDarkTheme()

    suspend fun setPreferenceIsDarkTheme(isDark: Boolean) {
        println("SetTheme: $isDark")
        preferencesRepository.setIsDarkTheme(isDark)
    }
}