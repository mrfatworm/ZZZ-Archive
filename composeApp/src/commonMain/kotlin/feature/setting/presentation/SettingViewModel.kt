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
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        initSetting()
    }

    private fun initSetting() {
        _isDark.value = themeUseCase.getPreferenceIsDarkTheme()
        updateUiScaleState()
        updateLanguageState()
        updateAppVersion()
    }

    fun onAction(action: SettingAction) {
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

    private fun updateLanguageState() {
        val newLanguage = languageUseCase.getLanguage()
        _uiState.update { it.copy(language = newLanguage) }
    }

    private fun updateUiScaleState() {
        _uiState.update {
            it.copy(
                uiScale = uiScaleUseCase.getUiScale(), fontScale = uiScaleUseCase.getFontScale()
            )
        }
    }

    private fun updateAppVersion() {
        _uiState.update { it.copy(appVersion = appInfoUseCase.getAppVersion()) }

    }

    private fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        themeUseCase.setPreferenceIsDarkTheme(isDark)
    }

    private fun setUiScale(uiScale: Float) {
        uiScaleUseCase.setUiScale(uiScale)
        updateUiScaleState()
    }

    private fun setFontScale(fontScale: Float) {
        uiScaleUseCase.setFontScale(fontScale)
        updateUiScaleState()
    }

    private fun setLanguage(langCode: String) {
        viewModelScope.launch {
            updateDatabaseUseCase.resetWikiDatabaseVersion()
        }
        languageUseCase.setLanguage(langCode)
        updateLanguageState()
    }

    private fun restartApp() {
        appActionsUseCase.restart()
    }
}