/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
    adaptiveLayoutType: AdaptiveLayoutType, onDriveClick: (Int) -> Unit, onBackClick: () -> Unit
) {
    val viewModel: DrivesListViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        DrivesListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onDriveClick = onDriveClick,
            onBackClick = onBackClick
        )
    } else {
        DrivesListScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onDriveClick = onDriveClick
        )
    }
}