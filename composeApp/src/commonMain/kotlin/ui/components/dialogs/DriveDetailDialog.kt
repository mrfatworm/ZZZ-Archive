/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import feature.drive.components.DriveDetailCard
import feature.drive.model.DriveListItem

@Composable
fun DriveDetailDialog(driveListItem: DriveListItem, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        DriveDetailCard(
            modifier = Modifier.widthIn(max = 512.dp, min = 240.dp).heightIn(max = 512.dp),
            driveListItem = driveListItem,
            onDismiss = onDismiss
        )
    }
}