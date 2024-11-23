/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.cover.components.CoverImageCard
import feature.home.AnnouncementBanner
import feature.home.model.HomeState
import feature.news.presentation.NewsPagerCard
import feature.pixiv.components.PixivTopicCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun HomeScreenSingle(
    uiState: HomeState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onPixivTagChange: (String) -> Unit,
    onActionClicked: () -> Unit,
    onClosed: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        AnnouncementBanner(
            uiState.banner,
            onActionClicked = onActionClicked,
            onClosed = onClosed
        )
        CoverImageCard(uiState.imageBanner)
        NewsPagerCard(uiState.newsList)
        PixivTopicCard(uiState.pixivPuppiesList, onPixivTagChange)
    }
}