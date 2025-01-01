/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.drive.components.DriveDetailCard
import feature.drive.components.DrivesListCard
import feature.drive.model.DrivesListState
import ui.utils.containerGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun DrivesListScreenDual(
    uiState: DrivesListState,
    onAction: (DrivesListAction) -> Unit,
) {
    Row(
        modifier = Modifier.padding(horizontalSafePadding()).padding(verticalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(containerGap())
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