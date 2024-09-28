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
import app.home.compose.BannerImageCard
import app.home.compose.DrivesListCard
import app.home.compose.HoYoLabZzzStatusCard
import app.home.compose.NewsPagerCard
import app.home.compose.PixivTopicCard
import app.home.compose.WEnginesListCard
import app.home.model.HomeState
import ui.theme.AppTheme
import ui.utils.NavigationType
import ui.utils.contentPadding

@Composable
fun HomeScreenDual(
    uiState: HomeState,
    navigationType: NavigationType,
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onDrivesOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Int) -> Unit = {},
    onWEngineDetailClick: (Int) -> Unit = {},
    onDriveDetailClick: (Int) -> Unit = {},
) {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(navigationType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            BannerImageCard(uiState.banner)
            HoYoLabZzzStatusCard()
            PixivTopicCard(uiState.pixivPuppiesList, navigationType)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(navigationType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            NewsPagerCard(uiState.news?.data)
            AgentsListCard(uiState.getAgentsNewToOld(), onAgentsOverviewClick, onAgentDetailClick)
            WEnginesListCard(uiState.wEnginesList, onWEnginesOverviewClick, onWEngineDetailClick)
            DrivesListCard(uiState.drivesList, onDrivesOverviewClick, onDriveDetailClick)
        }
    }
}