/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.wengine.model.WEngineDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun WEngineDetailScreen(onBackClick: () -> Unit) {
    val viewModel: WEngineDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.error != null) {
        ErrorScreen(
            uiState.error!!,
            onAction = { viewModel.onAction(WEngineDetailAction.Retry) },
            onBack = {
                onBackClick()
            })
    } else {
        WEngineDetailContent(uiState) { actions ->
            when (actions) {
                WEngineDetailAction.ClickBack -> onBackClick()
                else -> viewModel.onAction(actions)
            }
        }
    }
}

@Composable
private fun WEngineDetailContent(
    uiState: WEngineDetailState, onAction: (WEngineDetailAction) -> Unit
) {
    if (AppTheme.contentType == ContentType.Single) {
        WEngineDetailScreenSingle(uiState, onAction)
    } else {
        WEngineDetailScreenDual(uiState, onAction)
    }
}