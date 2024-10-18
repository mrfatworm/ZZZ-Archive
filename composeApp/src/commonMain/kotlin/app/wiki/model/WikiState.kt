/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wiki.model

import app.agent.model.AgentListItem
import app.bangboo.model.BangbooListItem
import app.drive.model.DriveListItem
import app.wengine.model.WEngineListItem

data class WikiState(
    val agentsList: List<AgentListItem> = emptyList(),
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val bangbooList: List<BangbooListItem> = emptyList(),
    val drivesList: List<DriveListItem> = emptyList()
)

