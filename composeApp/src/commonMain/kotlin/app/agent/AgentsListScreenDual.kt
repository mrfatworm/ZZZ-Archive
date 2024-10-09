/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.compose.AgentsListFilterCard
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
    onAgentDetailClick: (Int) -> Unit = {},
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(adaptiveLayoutType, AppTheme.dimens),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        AgentsListFilterCard(
            modifier = Modifier.weight(1f),
            uiState = uiState,
            onAgentDetailClick = onAgentDetailClick,
            onRarityChipSelectionChanged = onRarityChipSelectionChanged,
            onAttributeChipSelectionChanged = onAttributeChipSelectionChanged,
            onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged
        )
    }
}