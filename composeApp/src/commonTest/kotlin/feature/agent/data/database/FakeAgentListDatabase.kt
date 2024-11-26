/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.database

class FakeAgentListDao : AgentsListDao {
    private val agentsList = mutableListOf<AgentsListItemEntity>()

    override suspend fun setAgentsList(agentsList: List<AgentsListItemEntity>) {
        this.agentsList.clear()
        this.agentsList.addAll(agentsList)
    }

    override suspend fun getAgentsList(): List<AgentsListItemEntity> {
        return agentsList
    }

    override suspend fun deleteAgentsList() {
        agentsList.clear()
    }

}