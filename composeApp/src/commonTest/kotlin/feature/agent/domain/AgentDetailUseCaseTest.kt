/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.domain

import feature.agent.data.repository.FakeAgentRepository
import feature.agent.model.stubAgentDetail
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class AgentDetailUseCaseTest {

    private val agentRepository = FakeAgentRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val agentsDetailUseCase = AgentDetailUseCase(agentRepository, languageUseCase)


    @Test
    fun `Get agents list success`() = runTest {
        val result = agentsDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubAgentDetail)
    }

    @Test
    fun `Get agents list error`() = runTest {
        agentRepository.setError(true)
        val result = agentsDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}