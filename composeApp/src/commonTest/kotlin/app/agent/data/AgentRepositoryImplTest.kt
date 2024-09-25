/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.data

import app.agent.model.stubAgentsListResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttpClient
import utils.ZzzResult
import kotlin.test.Test

class AgentRepositoryImplTest {
    private val httpClient = FakeZzzHttpClient()
    private val repository = AgentRepositoryImpl(httpClient)

    @Test
    fun `Get Agents List Success`() = runTest {
        val result = repository.getAgentsList() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubAgentsListResponse)
    }

    @Test
    fun `Get Agents List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getAgentsList() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}