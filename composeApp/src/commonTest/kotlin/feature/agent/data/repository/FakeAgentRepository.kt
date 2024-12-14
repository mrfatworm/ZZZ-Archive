/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.repository

import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem
import feature.agent.model.stubAgentDetail
import feature.agent.model.stubAgentsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAgentRepository : AgentRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getAgentsList(languagePath: String): Flow<List<AgentListItem>> = flow {
        emit(stubAgentsList)
    }


    override suspend fun requestAndUpdateAgentsListDB(languagePath: String): Result<Unit> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun getAgentDetail(id: Int, languagePath: String): Result<AgentDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubAgentDetail)
        }
    }
}