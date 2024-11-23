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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.components.chips.SpecialtyFilterChips
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.drawColumnListMask
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
                modifier = Modifier.padding(top = AppTheme.dimens.paddingCard),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                items(count = uiState.agentsList.size,
                    key = { index -> uiState.agentsList[index].id }) { index ->
                    val agent = uiState.agentsList[index]
                    RarityItem(
                        modifier = Modifier.animateItem(),
                        rarityLevel = agent.rarity,
                        name = agent.name,
                        attribute = agent.getAttributeEnum(),
                        imgUrl = agent.getProfileUrl(),
                        onClick = {
                            onAgentClick(agent.id)
                        })
                }
            }
        }


    }
}