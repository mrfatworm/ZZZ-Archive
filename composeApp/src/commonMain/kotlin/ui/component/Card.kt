/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
    modifier: Modifier = Modifier,
    hasDefaultPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.background(
            AppTheme.colors.surfaceContainer, RoundedCornerShape(AppTheme.radius.contentCard)
        ).clip(RoundedCornerShape(AppTheme.radius.contentCard))
            .padding(if (hasDefaultPadding) AppTheme.dimens.paddingCard else 0.dp)

    ) {
        content()
    }
}

@Composable
fun CardHeader(
    modifier: Modifier, title: String, action: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier.heightIn(min = 56.dp).padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.titleMedium
        )
        Spacer(modifier.weight(1f))
        action()
    }
}

@Composable
fun HoveredIndicatorHeader(
    modifier: Modifier,
    titleRes: StringResource,
    viewAllTextRes: StringResource? = null,
    isHovered: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onViewAllClick: () -> Unit = {},
    action: @Composable RowScope.() -> Unit = {}
) {
    CardHeader(
        modifier = modifier.fillMaxWidth(), title = stringResource(titleRes)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = isHovered, enter = fadeIn(), exit = fadeOut()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ZzzIconButton(
                        iconRes = Res.drawable.ic_arrow_back,
                        contentDescriptionRes = Res.string.previous,
                        size = 32.dp
                    ) {
                        onPreviousClick()
                    }
                    ZzzIconButton(
                        iconRes = Res.drawable.ic_arrow_next,
                        contentDescriptionRes = Res.string.previous,
                        size = 32.dp
                    ) {
                        onNextClick()
                    }
                }
            }
            viewAllTextRes?.let {
                Text(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .clickable { onViewAllClick() }.pointerHoverIcon(PointerIcon.Hand)
                        .background(AppTheme.colors.surface)
                        .border(1.dp, AppTheme.colors.border, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    text = stringResource(viewAllTextRes),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
            }
            action()
        }
    }
}