/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import feature.drive.components.DrivesListCard
import feature.drive.data.database.emptyDriveListItemEntity
import feature.drive.model.DrivesListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.components.dialogs.DriveDetailDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.drives

@Composable
fun DrivesListScreenSingle(
    uiState: DrivesListState,
    onAction: (DrivesListAction) -> Unit,
) {
    val openDetailDialog = remember { mutableStateOf(false) }
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            TopBarScaffold(
                title = stringResource(Res.string.drives),
                onBackClick = {
                    onAction(DrivesListAction.ClickBack)
                }
            )
        }
    }) { contentPadding ->
        Column(
            modifier = Modifier.contentPaddingInScaffold(contentPadding)
        ) {
            DrivesListCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                onDriveClick = {
                    onAction(DrivesListAction.ClickDriveDetail(it))
                    openDetailDialog.value = true
                },
            )
        }
    }
    when {
        openDetailDialog.value -> {
            DriveDetailDialog(
                drivesListItemEntity = uiState.selectedDrive ?: emptyDriveListItemEntity,
                onDismiss = {
                    openDetailDialog.value = false
                })
        }
    }
}