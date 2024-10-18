/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import app.agent.model.AgentListItem
import app.bangboo.model.BangbooListItem
import app.drive.model.DriveListItem
import app.wengine.model.WEngineListItem
import mainfunc.model.BannerResponse

data class HomeState(
    val banner: BannerResponse? = null,
    val imageBanner: ImageBannerResponse? = null,
    val news: OfficialNewsResponse? = null,
    val pixivPuppiesList: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)

