/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

@Composable
fun ZzzPrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    iconRes: DrawableResource? = null,
    enabled: Boolean = true,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {}
) {
    ZzzButton(
        modifier = modifier,
        containerColor = AppTheme.colors.primary,
        contentColor = AppTheme.colors.onPrimary,
        disabledContainerColor = AppTheme.colors.onSurface.copy(alpha = 0.12f),
        disabledContentColor = AppTheme.colors.onSurfaceVariant,
        text = text,
        iconRes = iconRes,
        enabled = enabled,
        elevation = elevation,
        interactionSource = interactionSource,
        onClick = onClick,
    )
}

@Composable
fun ZzzOutlineButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    iconRes: DrawableResource? = null,
    enabled: Boolean = true,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {}
) {
    ZzzButton(
        modifier = modifier,
        containerColor = AppTheme.colors.surface,
        contentColor = AppTheme.colors.onSurface,
        disabledContainerColor = AppTheme.colors.surface.copy(alpha = 0.38f),
        disabledContentColor = AppTheme.colors.onSurface.copy(alpha = 0.38f),
        text = text,
        iconRes = iconRes,
        hasBorder = true,
        enabled = enabled,
        elevation = elevation,
        interactionSource = interactionSource,
        onClick = onClick
    )
}

@Composable
fun ZzzButton(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    disabledContainerColor: Color,
    disabledContentColor: Color,
    text: String = "Button",
    iconRes: DrawableResource? = null,
    hasBorder: Boolean = false,
    enabled: Boolean = true,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier.pointerHoverIcon(if (enabled) PointerIcon.Hand else PointerIcon.Default)
            .border(
            width = if (hasBorder) 3.dp else 0.dp,
            color = if (hasBorder) AppTheme.colors.buttonBorder else Color.Transparent,
            shape = CircleShape
        ),
        shape = RoundedCornerShape(64.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        enabled = enabled,
        elevation = elevation,
        interactionSource = interactionSource,
        onClick = onClick
    ) {
        iconRes?.let {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                imageVector = vectorResource(iconRes),
                contentDescription = null
            )
        }
        Text(
            text = text,
            style = AppTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}