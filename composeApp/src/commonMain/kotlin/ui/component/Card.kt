/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_arrow_next
import zzzarchive.composeapp.generated.resources.previous

@Composable
fun ContentCard(
    modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.background(
            AppTheme.colors.surfaceContainer, RoundedCornerShape(AppTheme.radius.contentCard)
        ).clip(RoundedCornerShape(AppTheme.radius.contentCard))
    ) {
        content()
    }
}

@Composable
fun CardHeader(
    modifier: Modifier, titleRes: StringResource, action: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier.heightIn(min = 56.dp).padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(titleRes),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
        action()
    }
}

@Composable
fun HoveredIndicatorHeader(
    modifier: Modifier,
    titleRes: StringResource,
    isHovered: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    CardHeader(
        modifier = modifier.fillMaxWidth(), titleRes = titleRes
    ) {
        if (isHovered) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_back,
                    textRes = Res.string.previous,
                    size = 32.dp
                ) {
                    onPreviousClick()
                }
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_next,
                    textRes = Res.string.previous,
                    size = 32.dp
                ) {
                    onNextClick()
                }
            }
        }
    }
}