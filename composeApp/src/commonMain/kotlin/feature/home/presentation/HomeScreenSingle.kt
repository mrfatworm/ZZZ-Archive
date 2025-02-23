/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.banner.components.AnnouncementBanner
import feature.cover_image.components.CoverImageCard
import feature.forum.compoenents.AllForumCard
import feature.forum.compoenents.TwitterCard
import feature.hoyolab.components.HoYoLabCard
import feature.news.components.NewsPagerCard
import feature.pixiv.components.PixivCard
import ui.navigation.Screen
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun HomeScreenSingle(
    uiState: HomeState,
    onAction: (HomeAction) -> Unit,
    onOpenBannerDialog: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .padding(horizontalSafePadding()).padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        AnnouncementBanner(
            uiState.banner,
            onActionClicked = onOpenBannerDialog,
            onClosed = {
                onAction(HomeAction.DismissBanner(it))
            }
        )
        CoverImageCard(uiState.coverImage)
        NewsPagerCard(uiState.newsList)
        HoYoLabCard(uiState.gameRecord, uiState.signResult, onSignClick = {
            onAction(HomeAction.Sign)
        }, onAddAccountClick = {
            onAction(HomeAction.NavigateTo(Screen.HoYoLabSync.route))
        }, onMyAgentClick = {
            onAction(HomeAction.NavigateTo(Screen.MyAgent.route))
        })
        PixivCard(uiState.pixivTopics) {
            onAction(HomeAction.ChangePixivTag(it))
        }
        TwitterCard(uiState.allForum.twitter)
        AllForumCard(uiState.allForum)
    }
}