/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.data.database.AgentsListDao
import feature.agent.data.mapper.toAgentDetail
import feature.agent.data.mapper.toAgentListItem
import feature.agent.data.mapper.toAgentsListItemEntity
import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class AgentRepositoryImpl(
    private val httpClient: ZzzHttp, private val agentsListDB: AgentsListDao
) : AgentRepository {

    override suspend fun getAgentsList(): Result<List<AgentListItem>> {
        return try {
            val localAgentsList = agentsListDB.getAgentsList()
            if (localAgentsList.isEmpty()) {
                val result = withTimeout(httpClient.defaultTimeout) {
                    httpClient.requestAgentsList()
                }
                agentsListDB.setAgentsList(result.agents.map { it.toAgentsListItemEntity() })
            }
            Result.success(agentsListDB.getAgentsList().map { it.toAgentListItem() }.reversed())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAgentDetail(id: Int): Result<AgentDetail> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentDetail(id)
            }
            Result.success(result.toAgentDetail())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}