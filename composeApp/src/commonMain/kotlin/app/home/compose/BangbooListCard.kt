/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.bangboo.model.BangbooListItem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItem
import ui.component.RowListFooterItem
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.all_bangboo
import zzzarchive.composeapp.generated.resources.bangboo

@Composable
fun BangbooListCard(
    bangbooList: List<BangbooListItem>, showViewAll: Boolean = false,
    onBangbooOverviewClick: () -> Unit,
    onBangbooDetailClick: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(modifier = Modifier.fillMaxWidth(),
            titleRes = Res.string.bangboo,
            viewAllTextRes = if (showViewAll) Res.string.all_bangboo else null,
            isHovered = isHovered.value,
            onPreviousClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex - 3
                coroutineScope.launch {
                    if (targetIndex >= 0) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            },
            onNextClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex + 3
                coroutineScope.launch {
                    if (targetIndex < bangbooList.size) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(bangbooList.size - 1)
                    }
                }
            },
            onViewAllClick = onBangbooOverviewClick
        )
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ),
            state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = bangbooList, key = { it.id }) { bangboo ->
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarityLevel = bangboo.rarity,
                    name = bangboo.name,
                    imgUrl = bangboo.getProfileUrl(),
                    onClick = {
                        onBangbooDetailClick(bangboo.id)
                    })
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
            item {
                RowListFooterItem(text = stringResource(Res.string.all_bangboo)) {
                    onBangbooOverviewClick()
                }
            }
        }
    }
}