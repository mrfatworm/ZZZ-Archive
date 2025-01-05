/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import feature.agent.model.RarityItem
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.components.items.RarityMiniItem
import ui.utils.cardPaddingWithHeader
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.suggest_w_engines

@Composable
fun SuggestWEnginesCard(wEnginesList: List<RarityItem>, wEngineClick: (Int) -> Unit) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(
            title = stringResource(Res.string.suggest_w_engines),
            isHovered = isHovered.value && (lazyListState.canScrollForward || lazyListState.canScrollBackward),
            lazyListState = lazyListState
        )
        LazyRow(
            state = lazyListState,
            contentPadding = cardPaddingWithHeader(),
            horizontalArrangement = rowListGap()
        ) {
            items(items = wEnginesList, key = { it.id }) { wEngine ->
                RarityMiniItem(
                    imgUrl = wEngine.getWEngineIconUrl(),
                    rarity = wEngine.getRarity()
                ) {
                    wEngineClick(wEngine.id)
                }
            }
        }
    }
}