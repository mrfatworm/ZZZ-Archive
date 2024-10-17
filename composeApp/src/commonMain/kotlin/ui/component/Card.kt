/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
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
    title: String, action: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.titleMedium
        )
        action()
    }
}

@Composable
fun HoveredIndicatorHeader(
    title: String,
    isHovered: Boolean,
    lazyListState: LazyListState,
    startContent: @Composable RowScope.() -> Unit = {},
    endContent: @Composable RowScope.() -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    CardHeader(title = title) {
        Row(
            Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            startContent()
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
                        size = 32.dp
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
}