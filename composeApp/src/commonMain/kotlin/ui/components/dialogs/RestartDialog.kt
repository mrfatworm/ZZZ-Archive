/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.restart
import zzzarchive.composeapp.generated.resources.restart_hint

@Composable
fun RestartDialog(onDismiss: () -> Unit, onRestart: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.widthIn(max = 320.dp).heightIn(max = 320.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.surfaceContainer,
                contentColor = AppTheme.colors.onSurfaceContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 32.dp, top = 32.dp, end = 32.dp, bottom = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.restart_hint),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.bodyMedium
                )
                ZzzPrimaryButton(text = stringResource(Res.string.restart)) {
                    onRestart()
                }
            }
        }
    }
}