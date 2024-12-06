/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

private val tagShape = RoundedCornerShape(8.dp)

@Composable
fun ZzzTag(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: DrawableResource? = null
) {
    Row(
        modifier = modifier.clip(tagShape).background(AppTheme.colors.surface).border(
            width = AppTheme.size.borderWidth,
            color = AppTheme.colors.border,
            shape = tagShape
        ).padding(horizontal = AppTheme.spacing.s350, vertical = AppTheme.spacing.s300),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconRes?.let {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSize),
                imageVector = vectorResource(iconRes),
                contentDescription = null,
                tint = AppTheme.colors.onSurface
            )
        }
        Text(
            text = text,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}