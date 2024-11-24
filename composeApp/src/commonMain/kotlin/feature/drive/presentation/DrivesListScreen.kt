/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.drive.components.DrivesListScreenDual
import feature.drive.components.DrivesListScreenSingle
import feature.drive.model.DrivesListState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import utils.UiResult

@Composable
fun DrivesListScreen(
    adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val drivesList = viewModel.drivesList.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (drivesList.value) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> ErrorScreen(
            message = (drivesList.value as UiResult.Error).message,
            onAction = {
                viewModel.onRetryClick()
            }
        )

        is UiResult.Success -> {
            DrivesListContent(adaptiveLayoutType, uiState, viewModel, onBackClick)
        }
    }
}

@Composable
private fun DrivesListContent(
    adaptiveLayoutType: AdaptiveLayoutType,
    uiState: State<DrivesListState>,
    viewModel: DrivesListViewModel,
    onBackClick: () -> Unit
) {
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        DrivesListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onDriveClick = {
                viewModel.onDriveClick(it)
            },
            onBackClick = onBackClick
        )
    } else {
        DrivesListScreenDual(
            uiState = uiState.value,
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