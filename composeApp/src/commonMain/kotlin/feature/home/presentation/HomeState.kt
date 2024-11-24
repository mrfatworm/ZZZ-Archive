/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.model

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.cover.data.CoverImageResponse
import feature.drive.model.DriveListItem
import feature.news.presentation.OfficialNewsState
import feature.pixiv.data.RecentArticle
import feature.wengine.model.WEngineListItem
import root.model.BannerResponse

data class HomeState(
    val banner: BannerResponse? = null,
    val imageBanner: CoverImageResponse? = null,
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivPuppiesList: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)
