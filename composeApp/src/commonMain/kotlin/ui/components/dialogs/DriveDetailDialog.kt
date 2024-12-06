/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import feature.drive.components.DriveDetailCard
import feature.drive.data.database.DrivesListItemEntity
import ui.theme.AppTheme

@Composable
fun DriveDetailDialog(drivesListItemEntity: DrivesListItemEntity, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        DriveDetailCard(
            modifier = Modifier.widthIn(
                max = AppTheme.size.maxDialogWidth, min = AppTheme.size.minDialogWidth
            ).heightIn(max = AppTheme.size.maxDialogHeight),
            drivesListItemEntity = drivesListItemEntity,
            onDismiss = onDismiss
        )
    }
}