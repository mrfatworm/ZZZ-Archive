/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.bangboo.model.BangbooListState
import ui.components.cards.ContentCard
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.drawColumnListMask
import utils.AgentAttribute
import utils.ZzzRarity

@Composable
fun BangbooListFilterCard(
    modifier: Modifier = Modifier,
    uiState: BangbooListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    invisibleFilter: Boolean = false,
    onBangbooClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit
) {
    ContentCard(
        modifier = modifier,
        hasDefaultPadding = false,
    ) {
        AnimatedVisibility(visible = !invisibleFilter) {
            Column(
                modifier = Modifier.padding(top = AppTheme.dimens.paddingCard),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RarityFilterChipsList(uiState.selectedRarity, onRarityChipSelectionChanged)
                AttributeFilterChipsList(
                    uiState.selectedAttributes,
                    1,
                    onAttributeChipSelectionChanged
                )
            }
        }

        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize().drawColumnListMask(
                colorScheme = AppTheme.colors,
                topEnable = lazyGridState.canScrollBackward,
                bottomEnable = lazyGridState.canScrollForward
            ),
            contentPadding = PaddingValues(AppTheme.dimens.paddingCard),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = uiState.bangbooList.size,
                key = { index -> uiState.bangbooList[index].id }) { index ->
                val bangboo = uiState.bangbooList[index]
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarityLevel = bangboo.rarity,
                    name = bangboo.name,
                    attribute = bangboo.getAttributeEnum(),
                    imgUrl = bangboo.getProfileUrl(),
                    onClick = {
                        onBangbooClick(bangboo.id)
                    })
            }
        }
    }
}