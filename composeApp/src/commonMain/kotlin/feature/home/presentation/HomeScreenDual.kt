/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import ui.theme.AppTheme
import ui.utils.containerGap
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun HomeScreenDual(
    uiState: HomeState, onAction: (HomeAction) -> Unit, onOpenBannerDialog: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontalSafePadding())
            .padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
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
                HoYoLabCard(uiState.gameRecord, uiState.signResult, onSignClick = {
                    onAction(HomeAction.Sign)
                }, onAddAccountClick = {
                    onAction(HomeAction.NavigateTo(Screen.HoYoLabSync.route))
                })
                PixivCard(uiState.pixivTopics) {
                    onAction(HomeAction.ChangePixivTag(it))
                }
                TwitterCard(uiState.allForum.twitter)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(contentGap())
            ) {
                NewsPagerCard(uiState.newsList)
                AllForumCard(uiState.allForum)
            }
        }
    }
}