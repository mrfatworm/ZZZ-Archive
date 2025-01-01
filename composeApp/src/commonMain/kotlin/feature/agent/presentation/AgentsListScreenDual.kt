/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.agent.components.AgentsListFilterCard
import feature.agent.components.FactionItem
import feature.agent.model.AgentsListState
import ui.theme.AppTheme
import ui.utils.containerGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun AgentsListScreenDual(
    uiState: AgentsListState,
    onAction: (AgentsListAction) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(containerGap())
    ) {
        AgentsListFilterCard(
            modifier = Modifier.weight(0.7f).padding(verticalSafePadding()),
            uiState = uiState,
            onAgentClick = {
                onAction(AgentsListAction.ClickAgent(it))
            },
            onRarityChipSelectionChanged = {
                onAction(AgentsListAction.ChangeRarityFilter(it))
            },
            onAttributeChipSelectionChanged = {
                onAction(AgentsListAction.ChangeAttributeFilter(it))
            },
            onSpecialtyChipSelectionChanged = {
                onAction(AgentsListAction.ChangeSpecialtyFilter(it))
            }
        )
        LazyColumn(
            modifier = Modifier.weight(0.3f),
            contentPadding = verticalSafePadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
        ) {
            items(items = uiState.factionsList, key = { it.id }) { faction ->
                FactionItem(faction, uiState.selectedFactionId == faction.id) {
                    onAction(AgentsListAction.ChangeFactionFilter(faction.id))
                }
            }
        }
    }
}