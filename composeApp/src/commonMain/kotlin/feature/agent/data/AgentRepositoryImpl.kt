/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.data.mapper.toAgentDetail
import feature.agent.data.mapper.toAgentListItem
import feature.agent.model.AgentDetail
import feature.agent.model.AgentListItem
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class AgentRepositoryImpl(private val httpClient: ZzzHttp) : AgentRepository {
    override suspend fun getAgentsList(): Result<List<AgentListItem>> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentsList()
            }
            Result.success(result.agents.map { it.toAgentListItem() })
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