/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzOutlineButton
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.retry

@Composable
fun ErrorScreen(
    message: String,
    actionName: String = stringResource(Res.string.retry),
    onAction: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            AppTheme.spacing.s300,
            Alignment.CenterVertically
        )
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.bodyLarge
        )
        ZzzPrimaryButton(text = actionName) {
            onAction()
        }

        ZzzOutlineButton(text = stringResource(Res.string.back)) {
            onBack()
        }
    }
}