/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import setting.SettingsRepository

class SettingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
        }
    }
}