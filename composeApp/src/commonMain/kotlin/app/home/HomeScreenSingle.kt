/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.home.compose.BannerImageCard
import app.home.compose.HoYoLabZzzStatusCard
import app.home.compose.PixivTrendingCard
import app.home.model.HomeState
import app.home.model.sampleHomeState
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme
import ui.utils.NavigationType

@Composable
fun HomeScreenSingle(
    uiState: HomeState,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onDrivesOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Int) -> Unit = {},
    onWEngineDetailClick: (Int) -> Unit = {},
    onDriveDetailClick: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = if (navigationType == NavigationType.NAVIGATION_RAIL) AppTheme.dimens.paddingParentOthersExpanded else AppTheme.dimens.paddingParentCompact),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        BannerImageCard(uiState.banner)
        HoYoLabZzzStatusCard()
        PixivTrendingCard()
    }
}


@Preview
@Composable
private fun PreviewHomeScreenSingle() {
    ZzzArchiveTheme {
        HomeScreenSingle(sampleHomeState)
    }
}

@Preview
@Composable
private fun PreviewHomeScreenSingleDark() {
    ZzzArchiveTheme(isDarkTheme = false) {
        HomeScreenSingle(sampleHomeState)
    }
}