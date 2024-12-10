/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.banner.components.AnnouncementBanner
import feature.cover_image.components.CoverImageCard
import feature.news.components.NewsPagerCard
import feature.pixiv.components.PixivTopicCard
import ui.utils.contentGap
import ui.utils.contentPadding

@Composable
fun HomeScreenSingle(
    uiState: HomeState,
    onAction: (HomeAction) -> Unit,
    onOpenBannerDialog: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(),
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
        PixivTopicCard(uiState.pixivTopics) {
            onAction(HomeAction.ChangePixivTag(it))
        }
    }
}