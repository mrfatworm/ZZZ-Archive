/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.repository

import feature.agent.data.database.FakeAgentListDao
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
    fun `WHEN Get agents list success THEN return local DB`() = runTest {
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `WHEN Request agents list success THEN return updated DB`() = runTest {
        repository.requestAndUpdateAgentsListDB()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 3)
    }

    @Test
    fun `GIVEN Agents list DB is empty WHEN Get agents list THEN Auto request and return updated DB`() =
        runTest {
        agentsListDao.deleteAgentsList()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 3)
    }

    @Test
    fun `WHEN Request agents list error THEN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `GIVEN Agents list DB is empty WHEN Request agents list error THEN return empty DB`() =
        runTest {
        httpClient.setError(true)
        agentsListDao.deleteAgentsList()
        val result = repository.getAgentsList().first()
        assertEquals(result.size, 0)
    }

    @Test
    fun `Get agent detail success`() = runTest {
        val result = repository.getAgentDetail(20).getOrNull()
        assertEquals(result, stubAgentDetail)
    }

    @Test
    fun `Get agent detail error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentDetail(20).getOrNull()
        assertNull(result)
    }
}