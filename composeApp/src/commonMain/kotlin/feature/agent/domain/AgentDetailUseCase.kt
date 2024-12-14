/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain

import feature.agent.data.repository.AgentRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.first


class AgentDetailUseCase(
    private val agentRepository: AgentRepository, private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke(id: Int) =
        agentRepository.getAgentDetail(id, languageUseCase.getLanguage().first().officialCode)
}