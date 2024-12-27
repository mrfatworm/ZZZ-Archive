/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import ui.utils.highlightText
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios

@Composable
fun ExpandableItem(title: String, subtitle: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Hand)
                .clickable { expanded = !expanded }
                .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = AppTheme.typography.titleSmall,
                color = AppTheme.colors.onSurfaceVariant
            )
            Text(
                modifier = Modifier.weight(1f),
                text = subtitle,
                textAlign = TextAlign.End,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurfaceVariant
            )
            Icon(
                modifier = Modifier.size(AppTheme.size.smallIconSize).graphicsLayer {
                    rotationZ = if (expanded) 180f else 0f
                },
                imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
        AnimatedVisibility(visible = expanded) {
            Text(
                modifier = Modifier.padding(
                    start = AppTheme.spacing.s400,
                    top = AppTheme.spacing.s300,
                    end = AppTheme.spacing.s400,
                    bottom = AppTheme.spacing.s500
                ),
                text = highlightText(description, AppTheme.colors),
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.onSurface
            )
        }
    }
}