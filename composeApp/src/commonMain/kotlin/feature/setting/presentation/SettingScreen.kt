/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.setting.model.SettingState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun SettingScreen(onFeedbackClick: () -> Unit, onHoYoLabClick: () -> Unit) {
    val viewModel: SettingViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingScreenContent(uiState) { action ->
        when (action) {
            SettingAction.ClickFeedback -> onFeedbackClick()
            SettingAction.ClickHoYoLab -> onHoYoLabClick()
            else -> viewModel.onAction(action)
        }
    }
}

@Composable
private fun SettingScreenContent(
    uiState: SettingState,
    onAction: (SettingAction) -> Unit
) {
    if (AppTheme.contentType == ContentType.Single) {
        SettingScreenSingle(uiState, onAction)
    } else {
        SettingScreenDual(uiState, onAction)
    }
}
