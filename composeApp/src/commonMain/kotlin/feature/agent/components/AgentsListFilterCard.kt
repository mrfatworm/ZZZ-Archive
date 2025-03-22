/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import feature.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.components.chips.SpecialtyFilterChips
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.cardPadding
import ui.utils.drawColumnListMask
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun AgentsListFilterCard(
    modifier: Modifier,
    uiState: AgentsListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    invisibleFilter: Boolean = false,
    onAgentClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
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
                AttributeFilterChipsList(
                    uiState.selectedAttributes,
                    1,
                    onAttributeChipSelectionChanged
                )
                SpecialtyFilterChips(
                    uiState.selectedSpecialties,
                    1,
                    onSpecialtyChipSelectionChanged
                )
            }
        }
        Box {
            if (uiState.selectedFactionId != 0) {
                val selectedFaction = uiState.factionsList[uiState.selectedFactionId - 1]
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd),
                    model = selectedFaction.getFactionFullUrl(),
                    contentDescription = stringResource(selectedFaction.getFactionNameRes()),
                    alpha = 0.7f
                )
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
                items(count = uiState.filteredAgentsList.size,
                    key = { index -> uiState.filteredAgentsList[index].id }) { index ->
                    val agent = uiState.filteredAgentsList[index]
                    RarityItem(
                        modifier = Modifier.animateItem(),
                        rarity = agent.rarity,
                        name = agent.name,
                        attribute = agent.attribute,
                        imgUrl = agent.imageUrl,
                        onClick = {
                            onAgentClick(agent.id)
                        })
                }
            }
        }
    }
}