/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.bangboo.model.BangbooDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun BangbooDetailScreen(onBackClick: () -> Unit) {
    val viewModel: BangbooDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.error != null) {
        ErrorScreen(
            message = uiState.error!!,
            onAction = { viewModel.onAction(BangbooDetailAction.Retry) },
            onBack = {
                onBackClick()
            })
    } else {
        BangbooDetailContent(uiState, onAction = { action ->
            when (action) {
                BangbooDetailAction.ClickBack -> onBackClick()
                else -> viewModel.onAction(action)
            }
        })
    }
}

@Composable
private fun BangbooDetailContent(
    uiState: BangbooDetailState, onAction: (BangbooDetailAction) -> Unit
) {
    if (AppTheme.contentType == ContentType.Single) {
        BangbooDetailScreenSingle(uiState, onAction)
    } else {
        BangbooDetailScreenDual(uiState, onAction)
    }
}