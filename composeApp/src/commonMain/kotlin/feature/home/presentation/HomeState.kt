/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.banner.data.BannerResponse
import feature.cover_image.data.CoverImageResponse
import feature.news.presentation.OfficialNewsState
import feature.pixiv.data.RecentArticle

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: CoverImageResponse? = null,
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivTopics: List<RecentArticle> = emptyList(),
)

