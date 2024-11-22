/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.domain

import androidx.lifecycle.ViewModel
import feature.setting.data.AppInfoRepository
import feature.setting.data.SettingsRepository
import feature.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import utils.AppActions

class SettingViewModel(
    private val settingsRepository: SettingsRepository,
    private val appInfoRepository: AppInfoRepository,
    private val appActions: AppActions,
    private val languageUseCase: LanguageUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(settingState)
    val uiState = _uiState.asStateFlow()
    private val _isDark = MutableStateFlow(false)
    val isDark = _isDark.asStateFlow()

    init {
        initSetting()
    }

    private fun initSetting() {
        _isDark.value = settingsRepository.getIsDarkTheme()
        updateLanguageState()
        getAppVersion()
    }

    private fun updateLanguageState() {
        val newLanguage = languageUseCase.getLanguage()
        _uiState.update { it.copy(language = newLanguage) }
    }

    private fun getAppVersion() {
        _uiState.update { it.copy(appVersion = appInfoRepository.getAppVersion()) }

    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        settingsRepository.setIsDarkTheme(isDark)
    }

    fun setLanguage(langCode: String) {
        languageUseCase.setLanguage(langCode)
        updateLanguageState()
    }

    fun restartApp() {
        appActions.restart()
    }
}