/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.domain

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import app.setting.model.settingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import setting.SettingsRepository
import utils.AppActions
import utils.Language
import utils.changeLanguage

class SettingViewModel(
    private val settingsRepository: SettingsRepository,
    private val appActions: AppActions
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
        val langCode = settingsRepository.getLanguage()
        updateLanguageState(langCode)
    }

    private fun updateLanguageState(
        langCode: String, defaultLanguage: String = Locale.current.language
    ) {
        val language =
            if (langCode == "") Language.entries.firstOrNull { it.project == defaultLanguage }
                ?: Language.English
            else Language.entries.firstOrNull { it.project == langCode } ?: Language.English
        _uiState.update { it.copy(language = language) }
    }

    fun setIsDarkTheme(isDark: Boolean) {
        _isDark.value = isDark
        settingsRepository.setIsDarkTheme(isDark)
    }

    fun setLanguage(langCode: String) {
        updateLanguageState(langCode)
        settingsRepository.setLanguage(langCode)
        changeLanguage(langCode)
    }

    fun restartApp() {
        appActions.restart()
    }
}