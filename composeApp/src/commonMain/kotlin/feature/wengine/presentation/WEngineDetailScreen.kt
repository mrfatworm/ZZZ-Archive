/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.wengine.components.WEngineDetailScreenDual
import feature.wengine.components.WEngineDetailScreenSingle
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
            onAction = { viewModel.onAction(WEngineDetailAction.OnRetry) }
        )
    } else {
        WEngineDetailContent(uiState, onBackClick)
        }
}

@Composable
private fun WEngineDetailContent(
    uiState: WEngineDetailState,
    onBackClick: () -> Unit
) {
    if (AppTheme.contentType == ContentType.Single) {
        WEngineDetailScreenSingle(
            uiState = uiState, onBackClick = onBackClick
        )
    } else {
        WEngineDetailScreenDual(
            uiState = uiState,
            onBackClick = onBackClick
        )
    }
}