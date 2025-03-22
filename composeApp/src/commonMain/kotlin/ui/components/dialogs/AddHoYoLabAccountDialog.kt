/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.hoyolab.components.AddHoYoLabAccountCard
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close

@Composable
fun AddHoYoLabAccountDialog(
    errorMessage: String, onSubmit: (String, String, String) -> Unit, onDismiss: () -> Unit
) {
    BasicDialog(onDismissRequest = {}) {
        Box(
            Modifier.fillMaxWidth()
                .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.add_account),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleMedium
            )
            ZzzIconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                iconRes = Res.drawable.ic_close,
                contentDescriptionRes = Res.string.close
            ) {
                onDismiss()
            }
        }
        AddHoYoLabAccountCard(errorMessage, onSubmit)
    }
}