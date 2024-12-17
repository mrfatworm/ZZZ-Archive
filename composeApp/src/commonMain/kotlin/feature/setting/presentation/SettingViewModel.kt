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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import utils.AppActionsUseCase

class SettingViewModel(
    private val themeUseCase: ThemeUseCase, private val uiScaleUseCase: UiScaleUseCase,
    private val appInfoUseCase: AppInfoUseCase,
    private val appActionsUseCase: AppActionsUseCase,
    private val languageUseCase: LanguageUseCase,
    private val updateDatabaseUseCase: UpdateDatabaseUseCase
) : ViewModel() {

    private var isDarkThemeJob: Job? = null
    private var languageJob: Job? = null
    private var uiScaleJob: Job? = null
    private var fontScaleJob: Job? = null

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.onStart {
        observeIsDarkTheme()
        observeLanguage()
        observeUiScale()
        observeFontScale()
        updateAppVersion()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _uiState.value)

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

    private fun observeIsDarkTheme() {
        isDarkThemeJob?.cancel()
        isDarkThemeJob = themeUseCase.getPreferenceIsDarkTheme().onEach { isDark ->
            _uiState.update { it.copy(isDark = isDark) }
        }.launchIn(viewModelScope)
    }

    private fun observeLanguage() {
        languageJob?.cancel()
        languageJob = languageUseCase.getLanguage().onEach { language ->
            _uiState.update { it.copy(language = language) }
        }.launchIn(viewModelScope)
    }

    private fun observeUiScale() {
        uiScaleJob?.cancel()
        uiScaleJob = uiScaleUseCase.getUiScale().onEach { uiScale ->
            _uiState.update { it.copy(uiScale = uiScale) }
        }.launchIn(viewModelScope)
    }

    private fun observeFontScale() {
        fontScaleJob?.cancel()
        fontScaleJob = uiScaleUseCase.getFontScale().onEach { fontScale ->
            _uiState.update { it.copy(fontScale = fontScale) }
        }.launchIn(viewModelScope)

    }

    private fun updateAppVersion() {
        _uiState.update { it.copy(appVersion = appInfoUseCase.getAppVersion()) }
    }

    private suspend fun setIsDarkTheme(isDark: Boolean) {
        themeUseCase.setPreferenceIsDarkTheme(isDark)
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