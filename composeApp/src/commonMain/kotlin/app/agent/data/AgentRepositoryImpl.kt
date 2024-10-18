/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.data

import app.agent.model.AgentDetailResponse
import app.agent.model.AgentsListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class AgentRepositoryImpl(private val httpClient: ZzzHttp) : AgentRepository {
    override suspend fun getAgentsList(): ZzzResult<AgentsListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentList()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

    override suspend fun getAgentDetail(id: Int): ZzzResult<AgentDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestAgentDetail(id)
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}