/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.CardHeader
import ui.component.ContentCard
import ui.theme.AppTheme

@Composable
fun TextCard(title: String, content: String, subTitle: String = "") {
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.dimens.paddingCard)
                .padding(bottom = AppTheme.dimens.paddingCard),
            text = content,
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.onSurface
        )
    }
}