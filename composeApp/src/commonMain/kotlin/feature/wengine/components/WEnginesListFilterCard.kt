/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.components

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
import feature.wengine.model.WEnginesListState
import ui.components.cards.ContentCard
import ui.components.chips.RarityFilterChipsList
import ui.components.chips.SpecialtyFilterChips
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.cardPadding
import ui.utils.drawColumnListMask
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun WEnginesListFilterCard(
    modifier: Modifier = Modifier,
    uiState: WEnginesListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    invisibleFilter: Boolean = false,
    onWEngineClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit
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
                SpecialtyFilterChips(
                    uiState.selectedSpecialties, 1, onSpecialtyChipSelectionChanged
                )
            }
        }
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Adaptive(AppTheme.size.rarityItemMediumSize),
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
                count = uiState.filteredWEnginesList.size,
                key = { index -> uiState.filteredWEnginesList[index].id }) { index ->
                val wEngine = uiState.filteredWEnginesList[index]
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarity = wEngine.rarity,
                    name = wEngine.name,
                    specialty = wEngine.specialty,
                    imgUrl = wEngine.imageUrl,
                    onClick = {
                        onWEngineClick(wEngine.id)
                    })
            }
        }
    }
}