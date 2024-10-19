/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.domain

import androidx.lifecycle.ViewModel
import app.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import setting.SettingsRepository

class SettingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.asStateFlow()
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        _isDark.value = settingsRepository.getIsDarkTheme()
    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        settingsRepository.setIsDarkTheme(isDark)
    }
}