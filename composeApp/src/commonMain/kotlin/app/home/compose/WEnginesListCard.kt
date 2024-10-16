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
import app.wengine.model.WEngineListItem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItem
import ui.component.RowListFooterItem
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.all_w_engines
import zzzarchive.composeapp.generated.resources.w_engines

@Composable
fun WEnginesListCard(
    wEnginesList: List<WEngineListItem>, showViewAll: Boolean = false,
    onWEnginesOverviewClick: () -> Unit,
    onWEngineDetailClick: (Int) -> Unit
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
            titleRes = Res.string.w_engines,
            viewAllTextRes = if (showViewAll) Res.string.all_w_engines else null,
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
                    if (lazyListState.canScrollForward) {
                        lazyListState.animateScrollToItem(targetIndex)
                    }
                }
            },
            onViewAllClick = onWEnginesOverviewClick
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
            items(items = wEnginesList, key = { it.id }) { wEngine ->
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarityLevel = wEngine.rarity,
                    name = wEngine.name,
                    imgUrl = wEngine.getImageUrl(),
                    specialty = wEngine.getSpecialtyEnum(),
                    onClick = {
                        onWEngineDetailClick(wEngine.id)
                    })
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
            item {
                RowListFooterItem(text = stringResource(Res.string.all_w_engines)) {
                    onWEnginesOverviewClick()
                }
            }
        }
    }
}