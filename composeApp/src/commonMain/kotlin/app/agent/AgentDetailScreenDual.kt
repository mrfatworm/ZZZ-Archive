/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.compose.AgentImageCard
import app.agent.model.AgentDetailState
import app.home.compose.HoYoLabCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun AgentDetailScreenDual(
    uiState: AgentDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
            ) {
                AgentImageCard(agentDetail = uiState.agentDetail, onBackClick = onBackClick)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
            ) {
                HoYoLabCard()
            }
        }
    }
}
