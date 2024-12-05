/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.components.cards.AgentsListCard
import ui.components.cards.BangbooListCard
import ui.components.cards.DrivesListCard
import ui.components.cards.WEnginesListCard
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun WikiScreenSingle(
    uiState: WikiState,
    onAction: (WikiAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentCompact)
    ) {
        AgentsListCard(
            agentsList = uiState.agentsList,
            showViewAll = true,
            onAgentsOverviewClick = {
                onAction(WikiAction.ClickAgentsOverview)
            },
            onAgentDetailClick = {
                onAction(WikiAction.ClickAgent(it))
            }
        )
        WEnginesListCard(
            wEnginesList = uiState.wEnginesList,
            showViewAll = true,
            onWEnginesOverviewClick = {
                onAction(WikiAction.ClickWEnginesOverview)
            },
            onWEngineDetailClick = {
                onAction(WikiAction.ClickWEngine(it))
            }
        )
        BangbooListCard(
            bangbooList = uiState.bangbooList,
            showViewAll = true,
            onBangbooOverviewClick = {
                onAction(WikiAction.ClickBangbooOverview)
            },
            onBangbooDetailClick = {
                onAction(WikiAction.ClickBangboo(it))
            }
        )
        DrivesListCard(
            drivesList = uiState.drivesList,
            showViewAll = true,
            onDrivesOverviewClick = {
                onAction(WikiAction.ClickDrivesOverview)
            }
        )
    }
}