/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.data

import app.agent.model.AgentsListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttpClient
import utils.ZzzResult

class AgentRepositoryImpl(private val httpClient: ZzzHttpClient) : AgentRepository {
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
}