/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_do_not_disturb_on

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZzzBottomSheet(
    titleRes: StringResource = Res.string.filter,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AppTheme.colors.surfaceContainer,
        contentColor = AppTheme.colors.onSurfaceContainer,
        dragHandle = null
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(AppTheme.spacing.s400),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconSize)
                        .graphicsLayer { rotationZ = 45f },
                    imageVector = vectorResource(Res.drawable.ic_do_not_disturb_on),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(titleRes),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconSize)
                        .graphicsLayer { rotationZ = 45f },
                    imageVector = vectorResource(Res.drawable.ic_do_not_disturb_on),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            content()
        }
    }
}