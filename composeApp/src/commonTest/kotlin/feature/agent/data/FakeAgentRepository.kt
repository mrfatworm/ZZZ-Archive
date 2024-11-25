/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.agent.model.stubAgentDetailResponse
import feature.agent.model.stubAgentsListResponse

class FakeAgentRepository : AgentRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAgentsList(): Result<AgentsListResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubAgentsListResponse)
        }
    }

    override suspend fun getAgentDetail(id: Int): Result<AgentDetailResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubAgentDetailResponse)
        }
    }
}