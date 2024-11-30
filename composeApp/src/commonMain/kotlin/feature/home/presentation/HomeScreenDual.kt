/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.banner.components.AnnouncementBanner
import feature.cover_image.components.CoverImageCard
import feature.news.presentation.NewsPagerCard
import feature.pixiv.components.PixivTopicCard
import ui.components.cards.AgentsListCard
import ui.components.cards.BangbooListCard
import ui.components.cards.DrivesListCard
import ui.components.cards.WEnginesListCard
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun HomeScreenDual(
    uiState: HomeState,
    onAgentsOverviewClick: () -> Unit,
    onWEnginesOverviewClick: () -> Unit,
    onBangbooOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit,
    onWEngineDetailClick: (Int) -> Unit,
    onBangbooDetailClick: (Int) -> Unit,
    onPixivTagChange: (String) -> Unit,
    onActionClicked: () -> Unit,
    onClosed: (Int) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .contentPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnnouncementBanner(
            uiState.banner,
            onActionClicked = onActionClicked,
            onClosed = onClosed
        )
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
            ) {
                CoverImageCard(uiState.coverImage)
                // HoYoLabCard()
                PixivTopicCard(uiState.pixivTopics, onPixivTagChange)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
            ) {
                NewsPagerCard(uiState.newsList)
                AgentsListCard(
                    agentsList = uiState.agentsList,
                    onAgentsOverviewClick = onAgentsOverviewClick,
                    onAgentDetailClick = onAgentDetailClick
                )
                WEnginesListCard(
                    wEnginesList = uiState.wEnginesList,
                    onWEnginesOverviewClick = onWEnginesOverviewClick,
                    onWEngineDetailClick = onWEngineDetailClick
                )
                BangbooListCard(
                    bangbooList = uiState.bangbooList,
                    onBangbooOverviewClick = onBangbooOverviewClick,
                    onBangbooDetailClick = onBangbooDetailClick
                )
                DrivesListCard(
                    drivesList = uiState.drivesList,
                    onDrivesOverviewClick = onDrivesOverviewClick,
                )
            }
        }
    }
}