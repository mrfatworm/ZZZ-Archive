/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.compose.AgentsListFilterCard
import app.agent.compose.FactionItem
import app.agent.model.AgentsListState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun AgentsListScreenDual(
    uiState: AgentsListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentClick: (Int) -> Unit = {},
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onFactionClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(adaptiveLayoutType, AppTheme.dimens),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        AgentsListFilterCard(
            modifier = Modifier.weight(0.7f),
            uiState = uiState,
            onAgentClick = onAgentClick,
            onRarityChipSelectionChanged = onRarityChipSelectionChanged,
            onAttributeChipSelectionChanged = onAttributeChipSelectionChanged,
            onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged
        )
        LazyColumn(
            modifier = Modifier.weight(0.3f), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = uiState.factionsList, key = { it.id }) { faction ->
                FactionItem(faction, uiState.selectedFactionId == faction.id) {
                    onFactionClick(faction.id)
                }
            }
        }
    }
}