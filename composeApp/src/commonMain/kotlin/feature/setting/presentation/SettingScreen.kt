/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.setting.components.SettingScreenDual
import feature.setting.components.SettingScreenSingle
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun SettingScreen(onFeedbackClick: () -> Unit) {
    val viewModel: SettingViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (AppTheme.contentType == ContentType.Single) {
        SettingScreenSingle(uiState, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        }, onFeedbackClick = onFeedbackClick, onRestart = {
            viewModel.restartApp()
        })
    } else {
        SettingScreenDual(uiState, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        }, onFeedbackClick = onFeedbackClick, onRestart = {
            viewModel.restartApp()
        })
    }
}
