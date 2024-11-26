/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.repository

import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem
import kotlinx.coroutines.flow.Flow

interface AgentRepository {
    suspend fun getAgentsList(): Flow<List<AgentListItem>>
    suspend fun requestAndUpdateAgentsListDB(): Result<Unit>
    suspend fun getAgentDetail(id: Int): Result<AgentDetail>
}