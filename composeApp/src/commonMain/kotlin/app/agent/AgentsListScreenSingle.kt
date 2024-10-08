/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.compose.AgentsListFilterCard
import app.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.component.ZzzTopBar
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents

@Composable
fun AgentsListScreenSingle(
    uiState: AgentsListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentDetailClick: (Int) -> Unit = {},
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzTopBar(title = stringResource(Res.string.agents), onBackClick = onBackClick)
        }
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
                .padding(AppTheme.dimens.paddingParentCompact)
        ) {
            AgentsListFilterCard(
                Modifier.fillMaxSize(),
                uiState,
                onAgentDetailClick,
                onRarityChipSelectionChanged,
                onAttributeChipSelectionChanged,
                onSpecialtyChipSelectionChanged
            )
        }
    }
}