/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem
import feature.agent.model.stubAgentDetail
import feature.agent.model.stubAgentsList

class FakeAgentRepository : AgentRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAgentsList(): Result<List<AgentListItem>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubAgentsList)
        }
    }

    override suspend fun getAgentDetail(id: Int): Result<AgentDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubAgentDetail)
        }
    }
}