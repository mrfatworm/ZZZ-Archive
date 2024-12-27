/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.AppTheme
import ui.utils.cardPaddingWithHeader
import ui.utils.highlightText

@Composable
fun HighLightTextCard(title: String, content: String, subTitle: String = "") {
    ContentCard(
        hasDefaultPadding = false
    ) {
        CardHeader(
            title = title
        ) {
            Text(
                text = subTitle,
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelMedium
            )
        }


        Text(
            modifier = Modifier.fillMaxWidth().padding(cardPaddingWithHeader()),
            text = highlightText(content, AppTheme.colors),
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.onSurface
        )
    }
}

