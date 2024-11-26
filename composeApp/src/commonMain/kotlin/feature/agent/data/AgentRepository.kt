/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem

interface AgentRepository {
    suspend fun getAgentsList(): Result<List<AgentListItem>>
    suspend fun getAgentDetail(id: Int): Result<AgentDetail>
}