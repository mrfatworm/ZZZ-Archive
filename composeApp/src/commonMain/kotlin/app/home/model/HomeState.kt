/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import app.agent.model.AgentListItem
import app.agent.model.stubAgentsListResponse
import ui.data.SimpleListItemState
import ui.data.sampleDrivesList
import ui.data.sampleWEnginesList

data class HomeState(
    val news: OfficialNewsResponse? = null,
    val banner: BannerResponse? = null,
    val pixivPuppiesList: List<RecentArticle> = emptyList(),
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<SimpleListItemState> = emptyList(),
    val drivesList: List<SimpleListItemState> = emptyList()
) {
    fun getAgentsNewToOld(): List<AgentListItem> {
        return agentsList.reversed()
    }
}


val sampleHomeState = HomeState(
    news = stubOfficialNewsDataResponse,
    banner = stubBannerResponse,
    agentsList = stubAgentsListResponse.agents,
    wEnginesList = sampleWEnginesList,
    drivesList = sampleDrivesList
)

