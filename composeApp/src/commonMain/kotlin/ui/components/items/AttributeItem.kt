/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

@Composable
fun AttributeItem(title: String, content: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
        Text(
            modifier = Modifier.weight(1f),
            text = content,
            textAlign = TextAlign.End,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.titleMedium
        )
    }
}