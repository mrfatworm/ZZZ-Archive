/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.data

import feature.agent.data.database.FakeAgentListDao
import feature.agent.data.repository.AgentRepositoryImpl
import feature.agent.model.stubAgentDetail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AgentRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val agentsListDao = FakeAgentListDao()
    private val repository = AgentRepositoryImpl(httpClient, agentsListDao)
    // Remote: 3 agents, Local: 1 agent

    @Test
    fun `Get Agents List Success`() = runTest {
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `Update Agents List Success`() = runTest {
        repository.requestAndUpdateAgentsListDB()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 3)
    }

    @Test
    fun `Get Agents List Empty DB `() = runTest {
        agentsListDao.deleteAgentsList()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 3)
    }

    @Test
    fun `Get Agents List Network Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `Get Agents List Network Error and Empty DB`() = runTest {
        httpClient.setError(true)
        agentsListDao.deleteAgentsList()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 0)
    }

    @Test
    fun `Get Agent Detail Success`() = runTest {
        val result = repository.getAgentDetail(20).getOrNull()
        assertEquals(result, stubAgentDetail)
    }

    @Test
    fun `Get Agent Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentDetail(20).getOrNull()
        assertNull(result)
    }
}