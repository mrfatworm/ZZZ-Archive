/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

@Composable
fun RowListEndItem(
    modifier: Modifier = Modifier.size(AppTheme.size.rarityItemMediumSize),
    text: String,
    radius: Dp = 8.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.clickable { onClick() }.background(
            Color(0xFF888888), RoundedCornerShape(radius)
        ).background(AppTheme.colors.hoveredMask, RoundedCornerShape(radius))
            .padding(AppTheme.spacing.s300),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.onHoveredMaskVariant
        )
    }
}