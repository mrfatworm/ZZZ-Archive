/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.data

import app.agent.model.AgentsListResponse
import utils.ZzzResult

interface AgentRepository {
    suspend fun getAgentsList(): ZzzResult<AgentsListResponse>
}