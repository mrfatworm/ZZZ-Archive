/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.banner.data.BannerResponse
import feature.cover_image.data.CoverImageResponse
import feature.drive.model.DriveListItem
import feature.news.presentation.OfficialNewsState
import feature.pixiv.data.RecentArticle
import feature.wengine.model.WEngineListItem

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: CoverImageResponse? = null,
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivTopics: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)

