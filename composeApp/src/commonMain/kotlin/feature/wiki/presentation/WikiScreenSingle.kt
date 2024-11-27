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
import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.drive.data.database.DrivesListItemEntity
import feature.wengine.model.WEnginesListItem
import ui.components.cards.AgentsListCard
import ui.components.cards.BangbooListCard
import ui.components.cards.DrivesListCard
import ui.components.cards.WEnginesListCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun WikiScreenSingle(
    agentsList: List<AgentListItem>,
    wEnginesList: List<WEnginesListItem>,
    bangbooList: List<BangbooListItem>,
    drivesList: List<DrivesListItemEntity>,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentsOverviewClick: () -> Unit,
    onWEnginesOverviewClick: () -> Unit,
    onBangbooOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit,
    onWEngineDetailClick: (Int) -> Unit,
    onBangbooDetailClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        AgentsListCard(
            agentsList = agentsList,
            showViewAll = true,
            onAgentsOverviewClick = onAgentsOverviewClick,
            onAgentDetailClick = onAgentDetailClick
        )
        WEnginesListCard(
            wEnginesList = wEnginesList,
            showViewAll = true,
            onWEnginesOverviewClick = onWEnginesOverviewClick,
            onWEngineDetailClick = onWEngineDetailClick
        )
        BangbooListCard(
            bangbooList = bangbooList,
            showViewAll = true,
            onBangbooOverviewClick = onBangbooOverviewClick,
            onBangbooDetailClick = onBangbooDetailClick
        )
        DrivesListCard(
            drivesList = drivesList,
            showViewAll = true,
            onDrivesOverviewClick = onDrivesOverviewClick
        )
    }
}