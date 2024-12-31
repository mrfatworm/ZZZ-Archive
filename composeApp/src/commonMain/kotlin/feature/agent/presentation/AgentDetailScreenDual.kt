/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.agent.components.AgentAttributesCard
import feature.agent.components.AgentImageCard
import feature.agent.components.AgentMaterialsCard
import feature.agent.components.CinemaCard
import feature.agent.components.GalleryCard
import feature.agent.components.SkillsCard
import feature.agent.components.SuggestDrivesCard
import feature.agent.components.SuggestWEnginesCard
import feature.agent.model.AgentDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.TextCard
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agent_background

@Composable
fun AgentDetailScreenDual(
    uiState: AgentDetailState,
    onAction: (AgentDetailAction) -> Unit,
) {
    Row(
        modifier = Modifier.background(AppTheme.colors.surface),
        horizontalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            AgentImageCard(uiState.agentDetail) {
                onAction(AgentDetailAction.ClickBack)
            }
            AgentAttributesCard(uiState.agentDetail)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            AgentMaterialsCard(uiState.agentDetail.levelMaterial)
            SuggestWEnginesCard(uiState.agentDetail.suggestWEngines) { id ->
                onAction(AgentDetailAction.ClickWEngine(id))
            }
            SuggestDrivesCard(uiState.agentDetail.suggestDrives, uiState.drivesList)
            SkillsCard(uiState.agentDetail)
            CinemaCard(uiState.agentDetail)
            TextCard(stringResource(Res.string.agent_background), uiState.agentDetail.agentBackground)
            GalleryCard(uiState.agentDetail)
        }
    }
}



