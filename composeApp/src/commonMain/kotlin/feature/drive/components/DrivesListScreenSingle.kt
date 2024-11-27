/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import feature.drive.data.database.emptyDriveListItemEntity
import feature.drive.model.DrivesListState
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTopBar
import ui.components.dialogs.DriveDetailDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.drives

@Composable
fun DrivesListScreenSingle(
    uiState: DrivesListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onDriveClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val openDetailDialog = remember { mutableStateOf(false) }
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzTopBar(
                title = stringResource(Res.string.drives),
                onBackClick = onBackClick
            )
        }
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
                .padding(AppTheme.dimens.paddingParentCompact)
        ) {
            DrivesListCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                onDriveClick = {
                    onDriveClick(it)
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