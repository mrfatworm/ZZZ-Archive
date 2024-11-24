/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse

interface AgentRepository {
    suspend fun getAgentsList(): Result<AgentsListResponse>
    suspend fun getAgentDetail(id: Int): Result<AgentDetailResponse>
}