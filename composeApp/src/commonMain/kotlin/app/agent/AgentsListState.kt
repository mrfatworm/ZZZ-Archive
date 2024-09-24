/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import ui.data.SimpleListItemState
import ui.data.sampleAgentsList

data class AgentsListState(
    val agentsList: List<SimpleListItemState> = emptyList()
)

val stubAgentsListState = AgentsListState(
    agentsList = sampleAgentsList
)
