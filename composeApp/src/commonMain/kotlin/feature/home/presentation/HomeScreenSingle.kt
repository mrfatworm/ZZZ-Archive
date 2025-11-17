/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.banner.components.AnnouncementBanner
import feature.cover.components.CoverImageCard
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
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontalSafePadding()),
        contentPadding = verticalSafePadding(),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        item {
            AnnouncementBanner(uiState.banner, onActionClicked = onOpenBannerDialog, onClosed = {
                onAction(HomeAction.DismissBanner(it))
            })
        }
        item {
            CoverImageCard(
                coverImages = uiState.coverImage,
                currentIndex = uiState.coverImageIndex,
                onIndexChange = { onAction(HomeAction.UpdateCoverImageIndex(it)) }
            )
        }
        item {
            NewsPagerCard(uiState.newsList)
        }
        item {
            HoYoLabCard(uiState.gameRecord, uiState.signResult, onSignClick = {
                onAction(HomeAction.Sign)
            }, onAddAccountClick = {
                onAction(HomeAction.NavigateTo(Screen.HoYoLabSync))
            }, onMyAgentClick = {
                onAction(HomeAction.NavigateTo(Screen.MyAgentsList))
            })
        }
        item {
            PixivCard(uiState.pixivTopics) {
                onAction(HomeAction.ChangePixivTag(it))
            }
        }
        item {
            TwitterCard(uiState.allForum.twitter)
        }
        item {
            AllForumCard(uiState.allForum)
        }
    }
}
