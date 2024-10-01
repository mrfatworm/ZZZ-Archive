/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model


data class AgentsListState(
    val agentsList: List<AgentListItem> = emptyList()
)

val stubAgentsListState = AgentsListState(
    agentsList = stubAgentsListResponse.agents
)
