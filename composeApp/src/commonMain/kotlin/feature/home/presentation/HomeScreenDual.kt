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
import feature.news.components.NewsPagerCard
import feature.pixiv.components.PixivTopicCard
import ui.components.cards.AgentsListCard
import ui.components.cards.BangbooListCard
import ui.components.cards.DrivesListCard
import ui.components.cards.WEnginesListCard
import ui.utils.containerGap
import ui.utils.contentGap
import ui.utils.contentPadding

@Composable
fun HomeScreenDual(
    uiState: HomeState, onAction: (HomeAction) -> Unit, onOpenBannerDialog: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).contentPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnnouncementBanner(uiState.banner, onActionClicked = onOpenBannerDialog, onClosed = {
            onAction(HomeAction.DismissBanner(it))
        })
        Row(horizontalArrangement = Arrangement.spacedBy(containerGap())) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(contentGap())
            ) {
                CoverImageCard(uiState.coverImage)
                // HoYoLabCard()
                PixivTopicCard(uiState.pixivTopics) {
                    onAction(HomeAction.ChangePixivTag(it))
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(contentGap())
            ) {
                NewsPagerCard(uiState.newsList)
                AgentsListCard(agentsList = uiState.agentsList, onAgentsOverviewClick = {
                    onAction(HomeAction.ClickAgentsOverview)
                }, onAgentDetailClick = {
                    onAction(HomeAction.ClickAgent(it))
                })
                WEnginesListCard(wEnginesList = uiState.wEnginesList, onWEnginesOverviewClick = {
                    onAction(HomeAction.ClickWEnginesOverview)
                }, onWEngineDetailClick = {
                    onAction(HomeAction.ClickWEngine(it))
                })
                BangbooListCard(bangbooList = uiState.bangbooList, onBangbooOverviewClick = {
                    onAction(HomeAction.ClickBangbooOverview)
                }, onBangbooDetailClick = {
                    onAction(HomeAction.ClickBangboo(it))
                })
                DrivesListCard(drivesList = uiState.drivesList) {
                    onAction(HomeAction.ClickDrivesOverview)
                }
            }
        }
    }
}