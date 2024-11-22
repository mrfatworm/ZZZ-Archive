/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.model

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.drive.model.DriveListItem
import feature.wengine.model.WEngineListItem

data class WikiState(
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)

