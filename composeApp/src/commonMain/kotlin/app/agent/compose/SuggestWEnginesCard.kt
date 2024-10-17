/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.agent.model.RarityItem
import kotlinx.coroutines.launch
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItemMini
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.suggest_w_engines

@Composable
fun SuggestWEnginesCard(wEnginesList: List<RarityItem>) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered, lazyListState)
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = wEnginesList, key = { it.id }) { wEngine ->
                RarityItemMini(
                    modifier = Modifier.animateItem(),
                    imgUrl = wEngine.getWEngineIconUrl(),
                    rarity = wEngine.getRarity()
                )
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
}

@Composable
private fun Header(
    isHovered: State<Boolean>, lazyListState: LazyListState
) {
    val coroutineScope = rememberCoroutineScope()
    HoveredIndicatorHeader(modifier = Modifier.fillMaxWidth(),
        titleRes = Res.string.suggest_w_engines,
        isHovered = isHovered.value && (lazyListState.canScrollForward || lazyListState.canScrollBackward),
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
        })
}