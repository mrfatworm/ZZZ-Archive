/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.model

import ui.data.SimpleListItemState
import ui.data.sampleAgentsList
import ui.data.sampleDriversList
import ui.data.sampleWEnginesList

data class HomeState(
    val activityImageUrl: String = "",
    val agentsList: List<SimpleListItemState> = emptyList(),
    val wEnginesList: List<SimpleListItemState> = emptyList(),
    val driversList: List<SimpleListItemState> = emptyList()
)


val stubHomeState = HomeState(
    activityImageUrl = "",
    agentsList = sampleAgentsList + sampleAgentsList + sampleAgentsList,
    wEnginesList = sampleWEnginesList,
    driversList = sampleDriversList
)

