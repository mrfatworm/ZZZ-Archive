/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.data

import app.agent.model.stubAgentDetailResponse
import app.agent.model.stubAgentsListResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AgentRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = AgentRepositoryImpl(httpClient)

    @Test
    fun `Get Agents List Success`() = runTest {
        val result = repository.getAgentsList() as ZzzResult.Success
        assertEquals(result.data, stubAgentsListResponse)
    }

    @Test
    fun `Get Agents List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentsList() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }

    @Test
    fun `Get Agent Detail Success`() = runTest {
        val result = repository.getAgentDetail(20) as ZzzResult.Success
        assertEquals(result.data, stubAgentDetailResponse)
    }

    @Test
    fun `Get Agent Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentDetail(20) as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}