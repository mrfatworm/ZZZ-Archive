/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.bangboo_speak

@Composable
fun ConfirmDialog(
    text: String,
    actionText: String = stringResource(Res.string.bangboo_speak),
    onAction: () -> Unit,
    onDismiss: () -> Unit
) {
    BasicDialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.padding(
                start = AppTheme.spacing.s500,
                top = AppTheme.spacing.s500,
                end = AppTheme.spacing.s500,
                bottom = AppTheme.spacing.s400
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s500)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = AppTheme.typography.bodyMedium
            )

            ZzzPrimaryButton(text = actionText) {
                onAction()
            }
        }
    }
}