/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.domain


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import app.agent.data.AgentRepository
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentDetailResponse
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AgentDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var agentRepository: AgentRepository
    private lateinit var viewModel: AgentDetailViewModel

    @BeforeTest
    fun setup() {
        savedStateHandle = SavedStateHandle().apply {
            set("agentId", 20)
        }
        agentRepository = FakeAgentRepository()
        viewModel = AgentDetailViewModel(savedStateHandle, agentRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentDetail, stubAgentDetailResponse)
    }
}