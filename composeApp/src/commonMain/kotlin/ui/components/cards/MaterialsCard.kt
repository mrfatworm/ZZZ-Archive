/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.cards

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import feature.agent.model.LevelMaterial
import org.jetbrains.compose.resources.stringResource
import ui.components.items.RarityMiniItem
import ui.utils.cardPaddingWithHeader
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.materials

@Composable
fun MaterialsListCard(materialsList: List<LevelMaterial>) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered.value, lazyListState)
        LazyRow(
            state = lazyListState,
            contentPadding = cardPaddingWithHeader(),
            horizontalArrangement = rowListGap()
        ) {
            items(items = materialsList, key = { it.id }) { material ->
                RarityMiniItem(
                    modifier = Modifier.animateItem(),
                    text = material.getAmountText(),
                    imgUrl = material.getProfileUrl()
                )
            }
        }
    }
}

@Composable
private fun Header(
    isHovered: Boolean,
    lazyListState: LazyListState
) {
    HoveredIndicatorHeader(
        title = stringResource(Res.string.materials),
        isHovered = isHovered && (lazyListState.canScrollForward || lazyListState.canScrollBackward),
        lazyListState = lazyListState
    )
}