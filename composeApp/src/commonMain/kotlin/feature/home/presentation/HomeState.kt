/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.bangboo.model.BangbooListItemResponse
import feature.banner.data.BannerResponse
import feature.cover_image.data.CoverImageResponse
import feature.drive.model.DriveListItemResponse
import feature.news.presentation.OfficialNewsState
import feature.pixiv.data.RecentArticle
import feature.wengine.model.WEngineListItem

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: CoverImageResponse? = null,
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivTopics: List<RecentArticle> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItemResponse> = emptyList(),
    val drivesList: List<DriveListItemResponse> = emptyList()
)

