/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.setting.compose.SettingScreenDual
import app.setting.compose.SettingScreenSingle
import app.setting.domain.SettingViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun SettingScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType
) {
    val viewModel: SettingViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    if (contentType == ContentType.Single) {
        SettingScreenSingle(uiState.value, adaptiveLayoutType, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        })
    } else {
        SettingScreenDual(uiState.value, adaptiveLayoutType, onColorChange = { isDark ->
            viewModel.setIsDarkTheme(isDark)
        }, onLanguageChange = { langCode ->
            viewModel.setLanguage(langCode)
        })
    }
}
