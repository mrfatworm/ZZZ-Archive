/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.home.compose.BannerImageCard
import app.home.compose.HoYoLabCard
import app.home.compose.NewsPagerCard
import app.home.compose.PixivTopicCard
import app.home.model.HomeState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun HomeScreenSingle(
    uiState: HomeState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onPixivTagChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        BannerImageCard(uiState.banner)
        HoYoLabCard()
        NewsPagerCard(uiState.news?.data)
        PixivTopicCard(uiState.pixivPuppiesList, adaptiveLayoutType, onPixivTagChange)
    }
}