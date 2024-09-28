/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

@Composable
fun ZzzIconButton(
    modifier: Modifier = Modifier,
    iconRes: DrawableResource,
    textRes: StringResource? = null,
    size: Dp = 24.dp,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit = {}
) {
    IconButton(modifier = modifier.background(AppTheme.colors.surface, CircleShape).border(
        width = AppTheme.dimens.borderWidth, color = AppTheme.colors.buttonBorder, CircleShape
    ), interactionSource = interactionSource, enabled = enabled, onClick = { onClick() }) {
        Icon(
            modifier = Modifier.size(size),
            imageVector = vectorResource(iconRes),
            contentDescription = if (textRes == null) null else stringResource(textRes),
            tint = AppTheme.colors.onSurface
        )
    }
}