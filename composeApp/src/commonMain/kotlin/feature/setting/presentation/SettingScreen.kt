/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import feature.setting.components.SettingScreenDual
import feature.setting.components.SettingScreenSingle
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun SettingScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onFeedbackClick: () -> Unit
) {
    val viewModel: SettingViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    if (contentType == ContentType.Single) {
        SettingScreenSingle(uiState.value, adaptiveLayoutType, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        }, onFeedbackClick = onFeedbackClick, onRestart = {
            viewModel.restartApp()
        })
    } else {
        SettingScreenDual(uiState.value, adaptiveLayoutType, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        }, onFeedbackClick = onFeedbackClick, onRestart = {
            viewModel.restartApp()
        })
    }
}
