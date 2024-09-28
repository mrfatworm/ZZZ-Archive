/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import app.home.model.sampleHomeState
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme
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

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(navigationType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            BannerImageCard(uiState.banner)
            HoYoLabZzzStatusCard()
            PixivTopicCard(uiState.pixivPuppiesList)
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

@Preview
@Composable
private fun PreviewHomeScreenDual() {
    ZzzArchiveTheme {
        HomeScreenDual(sampleHomeState, NavigationType.NAVIGATION_DRAWER)
    }
}

@Preview
@Composable
private fun PreviewHomeScreenDualDark() {
    ZzzArchiveTheme(isDarkTheme = false) {
        HomeScreenDual(sampleHomeState, NavigationType.NAVIGATION_DRAWER)
    }
}