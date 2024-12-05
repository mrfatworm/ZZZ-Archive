/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.drive.model.DrivesListState
import feature.drive.presentation.DrivesListAction
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun DrivesListScreenDual(
    uiState: DrivesListState,
    onAction: (DrivesListAction) -> Unit,
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        DrivesListCard(
            modifier = Modifier.weight(1f),
            uiState = uiState,
            onDriveClick = {
                onAction(DrivesListAction.ClickDriveDetail(it))
            }
        )
        uiState.selectedDrive?.let { driveListItem ->
            DriveDetailCard(
                modifier = Modifier.width(360.dp),
                drivesListItemEntity = driveListItem,
                onDismiss = {
                    onAction(DrivesListAction.DismissDriveDetail)
                }
            )
        }
    }
}