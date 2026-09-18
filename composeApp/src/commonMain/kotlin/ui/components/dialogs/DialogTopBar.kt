/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close

/** A dialog's header: the title centred, a close button at the trailing edge. */
@Composable
fun DialogTopBar(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
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
}
