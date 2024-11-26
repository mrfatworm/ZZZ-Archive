/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAgentListDao : AgentsListDao {
    private val agentsList = mutableListOf(stubAgentsListItemEntity)

    override suspend fun setAgentsList(agentsList: List<AgentsListItemEntity>) {
        this.agentsList.clear()
        this.agentsList.addAll(agentsList)
    }

    override fun getAgentsList(): Flow<List<AgentsListItemEntity>> = flow {
        emit(agentsList)
    }

    override suspend fun deleteAgentsList() {
        agentsList.clear()
    }
}