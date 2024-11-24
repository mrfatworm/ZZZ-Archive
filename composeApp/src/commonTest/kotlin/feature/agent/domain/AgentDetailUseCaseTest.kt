/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.domain

import feature.agent.data.FakeAgentRepository
import feature.agent.model.stubAgentDetailResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class AgentDetailUseCaseTest {

    private val agentRepository = FakeAgentRepository()
    private val agentsDetailUseCase = AgentDetailUseCase(agentRepository)


    @Test
    fun `Get Agents List Success`() = runTest {
        val result = agentsDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubAgentDetailResponse)
    }

    @Test
    fun `Get Agents List Fail`() = runTest {
        agentRepository.setError(true)
        val result = agentsDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}