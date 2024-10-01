/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.data

import app.agent.model.AgentsListResponse
import app.agent.model.stubAgentsListResponse
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
}