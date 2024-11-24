/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class AgentRepositoryImpl(private val httpClient: ZzzHttp) : AgentRepository {
    override suspend fun getAgentsList(): Result<AgentsListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentsList()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAgentDetail(id: Int): Result<AgentDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentDetail(id)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}