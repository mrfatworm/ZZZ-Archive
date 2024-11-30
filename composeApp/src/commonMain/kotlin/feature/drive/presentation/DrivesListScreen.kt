/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.drive.components.DrivesListScreenDual
import feature.drive.components.DrivesListScreenSingle
import feature.drive.model.DrivesListState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType

@Composable
fun DrivesListScreen(onBackClick: () -> Unit) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DrivesListContent(uiState = uiState, onDriveClick = {
        viewModel.onDriveClick(it)
    }, onDetailDismiss = {
        viewModel.onDetailDismiss()
    }, onBackClick = onBackClick
    )
}

@Composable
private fun DrivesListContent(
    uiState: DrivesListState,
    onDriveClick: (Int) -> Unit,
    onDetailDismiss: () -> Unit,
    onBackClick: () -> Unit
) {
    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        DrivesListScreenSingle(
            uiState = uiState, onDriveClick = onDriveClick, onBackClick = onBackClick
        )
    } else {
        DrivesListScreenDual(
            uiState = uiState,
            onDriveClick = onDriveClick,
            onDetailDismiss = onDetailDismiss,
        )
    }
}