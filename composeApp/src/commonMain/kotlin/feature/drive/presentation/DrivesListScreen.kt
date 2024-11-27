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
import ui.utils.AdaptiveLayoutType

@Composable
fun DrivesListScreen(
    adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DrivesListContent(adaptiveLayoutType, uiState, viewModel, onBackClick)
}

@Composable
private fun DrivesListContent(
    adaptiveLayoutType: AdaptiveLayoutType,
    uiState: DrivesListState,
    viewModel: DrivesListViewModel,
    onBackClick: () -> Unit
) {
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        DrivesListScreenSingle(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onDriveClick = {
                viewModel.onDriveClick(it)
            },
            onBackClick = onBackClick
        )
    } else {
        DrivesListScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onDriveClick = { id ->
                viewModel.onDriveClick(id)
            },
            onDetailDismiss = {
                viewModel.onDetailDismiss()
            }
        )
    }
}