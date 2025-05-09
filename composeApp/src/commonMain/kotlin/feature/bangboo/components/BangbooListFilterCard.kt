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
import feature.bangboo.model.BangbooListState
import ui.components.cards.ContentCard
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.cardPadding
import ui.utils.drawColumnListMask
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap
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
                modifier = Modifier.padding(top = cardPadding()),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
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
            columns = GridCells.Adaptive(AppTheme.size.s100),
            modifier = Modifier.fillMaxSize().drawColumnListMask(
                colorScheme = AppTheme.colors,
                topEnable = lazyGridState.canScrollBackward,
                bottomEnable = lazyGridState.canScrollForward
            ),
            contentPadding = PaddingValues(cardPadding()),
            horizontalArrangement = gridListHorizontalGap(),
            verticalArrangement = gridListVerticalGap()
        ) {
            items(
                count = uiState.filteredBangbooList.size,
                key = { index -> uiState.filteredBangbooList[index].id }) { index ->
                val bangboo = uiState.filteredBangbooList[index]
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarity = bangboo.rarity,
                    name = bangboo.name,
                    attribute = bangboo.attribute,
                    imgUrl = bangboo.imageUrl,
                    onClick = {
                        onBangbooClick(bangboo.id)
                    })
            }
        }
    }
}