/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.drive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.drive.compose.DrivesListScreenDual
import app.drive.compose.DrivesListScreenSingle
import app.drive.domain.DrivesListViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun DrivesListScreen(
    adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
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