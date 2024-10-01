/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.home.compose.AgentsListCard
import app.home.compose.BangbooListCard
import app.home.compose.BannerImageCard
import app.home.compose.DrivesListCard
import app.home.compose.HoYoLabZzzStatusCard
import app.home.compose.NewsPagerCard
import app.home.compose.PixivTopicCard
import app.home.compose.WEnginesListCard
import app.home.model.HomeState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun HomeScreenDual(
    uiState: HomeState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onBangbooOverviewClick: () -> Unit = {},
    onDrivesOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Int) -> Unit = {},
    onWEngineDetailClick: (Int) -> Unit = {},
    onBangbooDetailClick: (Int) -> Unit = {},
    onDriveDetailClick: (Int) -> Unit = {},
) {
    Row(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            BannerImageCard(uiState.banner)
            HoYoLabZzzStatusCard()
            PixivTopicCard(uiState.pixivPuppiesList, adaptiveLayoutType)
        }

        Column(
            modifier = Modifier.weight(1f)
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            NewsPagerCard(uiState.news?.data)
            AgentsListCard(uiState.agentsList, onAgentsOverviewClick, onAgentDetailClick)
            WEnginesListCard(uiState.wEnginesList, onWEnginesOverviewClick, onWEngineDetailClick)
            BangbooListCard(uiState.bangbooList, onBangbooOverviewClick, onBangbooDetailClick)
            DrivesListCard(uiState.drivesList, onDrivesOverviewClick, onDriveDetailClick)
        }
    }
}