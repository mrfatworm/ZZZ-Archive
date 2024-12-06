/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.drive.model.DrivesListState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType

@Composable
fun DrivesListScreen(onBackClick: () -> Unit) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DrivesListContent(uiState) { actions ->
        when (actions) {
            DrivesListAction.ClickBack -> onBackClick()
            else -> viewModel.onAction(actions)
        }
    }
}

@Composable
private fun DrivesListContent(
    uiState: DrivesListState,
    onAction: (DrivesListAction) -> Unit,
) {
    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        DrivesListScreenSingle(uiState, onAction)
    } else {
        DrivesListScreenDual(uiState = uiState, onAction)
    }
}