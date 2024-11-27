/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.UpdateDatabaseUseCase
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.domain.ThemeUseCase
import feature.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.AppActionsUseCase

class SettingViewModel(
    private val themeUseCase: ThemeUseCase,
    private val appInfoUseCase: AppInfoUseCase,
    private val appActionsUseCase: AppActionsUseCase,
    private val languageUseCase: LanguageUseCase,
    private val updateDatabaseUseCase: UpdateDatabaseUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.asStateFlow()
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        initSetting()
    }

    private fun initSetting() {
        _isDark.value = themeUseCase.getPreferenceIsDarkTheme()
        updateLanguageState()
        getAppVersion()
    }

    private fun updateLanguageState() {
        val newLanguage = languageUseCase.getLanguage()
        _uiState.update { it.copy(language = newLanguage) }
    }

    private fun getAppVersion() {
        _uiState.update { it.copy(appVersion = appInfoUseCase.getAppVersion()) }

    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        themeUseCase.setPreferenceIsDarkTheme(isDark)
    }

    fun setLanguage(langCode: String) {
        viewModelScope.launch {
            updateDatabaseUseCase.resetWikiDatabaseVersion()
        }
        languageUseCase.setLanguage(langCode)
        updateLanguageState()
    }

    fun restartApp() {
        appActionsUseCase.restart()
    }
}