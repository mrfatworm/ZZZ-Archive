/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.agent.model.stubAgentDetailResponse
import feature.agent.model.stubAgentsListResponse
import utils.ZzzResult

class FakeAgentRepository : AgentRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAgentsList(): ZzzResult<AgentsListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubAgentsListResponse)
        }
    }

    override suspend fun getAgentDetail(id: Int): ZzzResult<AgentDetailResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubAgentDetailResponse)
        }
    }
}