/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import app.agent.model.AgentListItem
import app.agent.model.stubAgentsListResponse
import app.bangboo.model.BangbooListItem
import app.drive.model.DriveListItem
import app.drive.model.stubDriveListResponse
import app.wengine.model.WEngineListItem
import app.wengine.model.stubWEnginesListResponse

data class HomeState(
    val news: OfficialNewsResponse? = null,
    val banner: BannerResponse? = null,
    val pixivPuppiesList: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)

val sampleHomeState = HomeState(
    news = stubOfficialNewsDataResponse,
    banner = stubBannerResponse,
    agentsList = stubAgentsListResponse.agents,
    wEnginesList = stubWEnginesListResponse.wEngines,
    drivesList = stubDriveListResponse.drives
)

