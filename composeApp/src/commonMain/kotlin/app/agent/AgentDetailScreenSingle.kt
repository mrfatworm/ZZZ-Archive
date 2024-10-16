/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.compose.AgentImageCard
import app.agent.compose.AgentPromoteMaterialCard
import app.agent.compose.AttributesCard
import app.agent.compose.CinemaCard
import app.agent.compose.SkillsCard
import app.agent.model.AgentDetailState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun AgentDetailScreenSingle(
    uiState: AgentDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        AgentImageCard(uiState.agentDetail, onBackClick)
        AttributesCard(uiState.agentDetail)
        AgentPromoteMaterialCard(uiState.agentDetail.levelMaterial)
        SkillsCard(uiState.agentDetail)
        CinemaCard(uiState.agentDetail)
    }
}