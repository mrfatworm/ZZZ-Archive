/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.repository

import feature.agent.model.AgentListItem
import feature.agent.model.stubAgentsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAgentRepository : AgentRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAgentsList(): Flow<List<AgentListItem>> = flow {
        emit(stubAgentsList)
    }

    override suspend fun requestAndUpdateAgentsListDB(): Result<Unit> = if (isError) {
        Result.failure(Exception())
    } else {
        Result.success(Unit)
    }
}
