/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.repository

import feature.agent.data.database.AgentsListDao
import feature.agent.data.mapper.toAgentListItem
import feature.agent.data.mapper.toAgentsListItemEntity
import feature.agent.model.AgentListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import network.ZzzHttp

class AgentRepositoryImpl(private val httpClient: ZzzHttp, private val agentsListDB: AgentsListDao) : AgentRepository {
    override suspend fun getAgentsList(): Flow<List<AgentListItem>> {
        val cachedAgentsList = agentsListDB.getAgentsList()
        if (cachedAgentsList.first().isEmpty()) {
            requestAndUpdateAgentsListDB()
        }
        return agentsListDB.getAgentsList().map { localAgentsList ->
            localAgentsList.map { it.toAgentListItem() }.reversed()
        }
    }

    override suspend fun requestAndUpdateAgentsListDB(): Result<Unit> {
        try {
            val result = httpClient.requestAgentsList()
            agentsListDB.setAgentsList(result.agents.map { it.toAgentsListItemEntity() })
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
