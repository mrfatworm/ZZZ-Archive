/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import feature.agent.model.AgentListItem
import feature.bangboo.model.BangbooListItem
import feature.drive.data.database.DrivesListItemEntity
import feature.wengine.model.WEnginesListItem

data class WikiState(
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEnginesListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DrivesListItemEntity> = emptyList()
)
