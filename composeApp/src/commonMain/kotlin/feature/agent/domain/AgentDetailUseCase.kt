/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain

import feature.agent.data.AgentRepository


class AgentDetailUseCase(
    private val agentRepository: AgentRepository
) {
    suspend fun invoke(id: Int) = agentRepository.getAgentDetail(id)
}