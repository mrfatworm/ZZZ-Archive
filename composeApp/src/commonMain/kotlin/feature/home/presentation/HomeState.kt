/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.banner.data.BannerResponse
import feature.cover_image.data.database.CoverImageListItemEntity
import feature.drive.data.database.DrivesListItemEntity
import feature.news.model.OfficialNewsState
import feature.pixiv.data.RecentArticle
import feature.wengine.model.WEnginesListItem

data class HomeState(
    val banner: BannerResponse? = null,
    val coverImage: List<CoverImageListItemEntity> = emptyList(),
    val newsList: List<OfficialNewsState> = emptyList(),
    val pixivTopics: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEnginesListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DrivesListItemEntity> = emptyList()
)

