/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.model.AgentDetailState
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agent_background

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
        AgentMaterialsCard(uiState.agentDetail.levelMaterial)
        SkillsCard(uiState.agentDetail)
        CinemaCard(uiState.agentDetail)
        SuggestWEnginesCard(uiState.agentDetail.suggestWEngines)
        SuggestDrivesCard(uiState.agentDetail.suggestDrives)
        TextCard(stringResource(Res.string.agent_background), uiState.agentDetail.agentBackground)
    }
}