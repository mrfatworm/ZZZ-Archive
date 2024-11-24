/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain

import feature.agent.data.AgentRepository
import feature.agent.model.AgentDetailResponse


class AgentDetailUseCase(
    private val agentRepository: AgentRepository
) {

    suspend fun invoke(id: Int): Result<AgentDetailResponse> {
        return agentRepository.getAgentDetail(id)
    }
}