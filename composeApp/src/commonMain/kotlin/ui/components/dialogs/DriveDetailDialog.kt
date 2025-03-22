/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.runtime.Composable
import feature.drive.components.DriveDetailCard
import feature.drive.data.database.DrivesListItemEntity

@Composable
fun DriveDetailDialog(drivesListItemEntity: DrivesListItemEntity, onDismiss: () -> Unit) {
    BasicDialog(onDismissRequest = onDismiss) {
        DriveDetailCard(
            drivesListItemEntity = drivesListItemEntity,
            onDismiss = onDismiss
        )
    }
}