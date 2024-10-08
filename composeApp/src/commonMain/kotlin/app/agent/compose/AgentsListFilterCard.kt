/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentsListState
import ui.component.AttributeFilterChips
import ui.component.ContentCard
import ui.component.RarityFilterChips
import ui.component.RarityItem
import ui.component.SpecialtyFilterChips
import ui.theme.AppTheme
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun AgentsListFilterCard(
    modifier: Modifier,
    uiState: AgentsListState,
    onAgentDetailClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit
) {
    ContentCard(
        modifier = modifier,
        hasDefaultPadding = false,
    ) {
        Column(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingCard),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RarityFilterChips(uiState.selectedRarity, onRarityChipSelectionChanged)

            AttributeFilterChips(uiState.selectedAttributes, onAttributeChipSelectionChanged)
            SpecialtyFilterChips(uiState.selectedSpecialties, onSpecialtyChipSelectionChanged)
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppTheme.dimens.paddingCard),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = uiState.agentsList.size,
                key = { index -> uiState.agentsList[index].id }) { index ->
                val agent = uiState.agentsList[index]
                RarityItem(rarityLevel = agent.rarity,
                    name = agent.name,
                    attribute = agent.getAttributeEnum(),
                    imgUrl = agent.getProfileUrl(),
                    onClick = { id ->
                        onAgentDetailClick(id)
                    })
            }
        }
    }
}