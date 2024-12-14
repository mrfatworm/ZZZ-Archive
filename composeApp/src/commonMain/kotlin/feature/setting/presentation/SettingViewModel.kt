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
import feature.setting.domain.UiScaleUseCase
import feature.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.AppActionsUseCase

class SettingViewModel(
    private val themeUseCase: ThemeUseCase, private val uiScaleUseCase: UiScaleUseCase,
    private val appInfoUseCase: AppInfoUseCase,
    private val appActionsUseCase: AppActionsUseCase,
    private val languageUseCase: LanguageUseCase,
    private val updateDatabaseUseCase: UpdateDatabaseUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.asStateFlow()
    private val _isDark = themeUseCase.getPreferenceIsDarkTheme()
    val isDark = _isDark

    init {
        viewModelScope.launch {
            launch { observeUiScale() }
            launch { observeFontScale() }
            launch { observeLanguage() }
            launch { updateAppVersion() }
        }
    }

    suspend fun onAction(action: SettingAction) {
        when (action) {
            is SettingAction.ChangeToDarkTheme -> {
                setIsDarkTheme(action.isDark)
            }

            is SettingAction.ChangeLanguage -> {
                setLanguage(action.langCode)
            }

            is SettingAction.RestartApp -> {
                restartApp()
            }

            is SettingAction.ScaleUi -> {
                setUiScale(action.uiScale)
                setFontScale(action.fontScale)
            }

            else -> {}
        }
    }

    private suspend fun observeLanguage() {
        languageUseCase.getLanguage().collect { language ->
            _uiState.update { it.copy(language = language) }
        }
    }

    private suspend fun observeUiScale() {
        uiScaleUseCase.getUiScale().collect { uiScale ->
            _uiState.update { it.copy(uiScale = uiScale) }
        }
    }

    private suspend fun observeFontScale() {
        uiScaleUseCase.getFontScale().collect { fontScale ->
            _uiState.update { it.copy(fontScale = fontScale) }
        }

    }

    private fun updateAppVersion() {
        _uiState.update { it.copy(appVersion = appInfoUseCase.getAppVersion()) }

    }

    private fun setIsDarkTheme(isDark: Boolean) {
        viewModelScope.launch {
            themeUseCase.setPreferenceIsDarkTheme(isDark)
        }
    }

    private suspend fun setUiScale(uiScale: Float) {
        uiScaleUseCase.setUiScale(uiScale)
    }

    private suspend fun setFontScale(fontScale: Float) {
        uiScaleUseCase.setFontScale(fontScale)
    }

    private suspend fun setLanguage(langCode: String) {
        updateDatabaseUseCase.resetWikiDatabaseVersion()
        languageUseCase.setLanguage(langCode)
    }

    private fun restartApp() {
        appActionsUseCase.restart()
    }
}