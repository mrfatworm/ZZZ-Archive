/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

private val zzzChipShape = CircleShape

@Composable
fun ZzzFilterChip(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: DrawableResource? = null,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clip(zzzChipShape).pointerHoverIcon(PointerIcon.Hand)
            .clickable(onClick = onClick).background(
                color = if (selected) AppTheme.colors.primaryContainer else AppTheme.colors.surface
            ).border(
                width = 1.dp,
                color = if (selected) Color.Transparent else AppTheme.colors.border,
                shape = zzzChipShape
            ).padding(
                horizontal = AppTheme.spacing.s350,
                vertical = AppTheme.spacing.s250
            ), horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconRes?.let {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSize),
                imageVector = vectorResource(iconRes),
                contentDescription = null,
                tint = if (selected) AppTheme.colors.onPrimaryContainer else AppTheme.colors.onSurface
            )
        }
        Text(
            text = text,
            color = if (selected) AppTheme.colors.onPrimaryContainer else AppTheme.colors.onSurface,
            style = AppTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}