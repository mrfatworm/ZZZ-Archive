/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.cardPadding
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
        modifier = modifier.clip(AppTheme.shape.r400)
            .background(AppTheme.colors.surfaceContainer)
            .padding(if (hasDefaultPadding) cardPadding() else 0.dp)
    ) {
        content()
    }
}

@Composable
fun CardHeader(
    title: String,
    startContent: @Composable RowScope.() -> Unit = {},
    endContent: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        startContent()
        Text(
            text = title.uppercase(),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
        Spacer(Modifier.weight(1f))
        endContent()
    }
}

@Composable
fun HoveredIndicatorHeader(
    title: String?,
    isHovered: Boolean,
    lazyListState: LazyListState,
    startContent: @Composable RowScope.() -> Unit = {},
    endContent: @Composable RowScope.() -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        title?.let {
            Text(
                text = title.uppercase(),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelLarge
            )
        }
        startContent()
        Spacer(Modifier.weight(1f))
        AnimatedVisibility(visible = isHovered, enter = fadeIn(), exit = fadeOut()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_back,
                    contentDescriptionRes = Res.string.previous,
                    size = AppTheme.size.smallIconButtonSize
                ) {
                    val targetIndex = lazyListState.firstVisibleItemIndex - 3
                    coroutineScope.launch {
                        if (targetIndex >= 0) {
                            lazyListState.animateScrollToItem(targetIndex)
                        } else {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                }
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_next,
                    contentDescriptionRes = Res.string.previous,
                    size = AppTheme.size.smallIconButtonSize
                ) {
                    val targetIndex = lazyListState.firstVisibleItemIndex + 3
                    coroutineScope.launch {
                        if (lazyListState.canScrollForward) {
                            lazyListState.animateScrollToItem(targetIndex)
                        }
                    }
                }
            }
        }
        endContent()
    }
}