/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.model.stubAgentDetailResponse
import feature.agent.model.stubAgentsListResponse
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AgentRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = AgentRepositoryImpl(httpClient)

    @Test
    fun `Get Agents List Success`() = runTest {
        val result = repository.getAgentsList().getOrNull()
        assertEquals(result, stubAgentsListResponse)
    }

    @Test
    fun `Get Agents List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentsList().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get Agent Detail Success`() = runTest {
        val result = repository.getAgentDetail(20).getOrNull()
        assertEquals(result, stubAgentDetailResponse)
    }

    @Test
    fun `Get Agent Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentDetail(20).getOrNull()
        assertNull(result)
    }
}