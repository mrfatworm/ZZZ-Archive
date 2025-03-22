/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

@Composable
fun ZzzIconButton(
    modifier: Modifier = Modifier,
    iconRes: DrawableResource,
    contentDescriptionRes: StringResource? = null,
    tint: Color = AppTheme.colors.onSurface,
    size: Dp = AppTheme.size.iconButton,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
) {
    Surface(modifier = modifier.pointerHoverIcon(if (enabled) PointerIcon.Hand else PointerIcon.Default).size(size),
        color = AppTheme.colors.surface,
        border = BorderStroke(
            width = AppTheme.size.borderWidth, color = AppTheme.colors.border
        ),
        shape = CircleShape,
        interactionSource = interactionSource,
        enabled = enabled,
        onClick = { onClick() }) {
        Icon(
            modifier = Modifier.padding(AppTheme.spacing.s300),
            imageVector = vectorResource(iconRes),
            contentDescription = if (contentDescriptionRes == null) null else stringResource(
                contentDescriptionRes
            ),
            tint = tint.copy(alpha = if (enabled) 1f else 0.38f)
        )
    }
}