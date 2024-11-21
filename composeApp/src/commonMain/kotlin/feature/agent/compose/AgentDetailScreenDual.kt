/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.agent.model.AgentDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.TextCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agent_background

@Composable
fun AgentDetailScreenDual(
    uiState: AgentDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    wEngineClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            AgentImageCard(agentDetail = uiState.agentDetail, onBackClick = onBackClick)
            AgentAttributesCard(uiState.agentDetail)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            AgentMaterialsCard(uiState.agentDetail.levelMaterial)
            SuggestWEnginesCard(uiState.agentDetail.suggestWEngines, wEngineClick)
            SuggestDrivesCard(uiState.agentDetail.suggestDrives, uiState.drivesList)
            SkillsCard(uiState.agentDetail)
            CinemaCard(uiState.agentDetail)
            TextCard(stringResource(Res.string.agent_background), uiState.agentDetail.agentBackground)
            GalleryCard(uiState.agentDetail)
        }
    }
}



