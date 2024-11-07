/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.domain


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentDetailResponse
import app.drive.data.FakeDriveRepository
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AgentDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle = SavedStateHandle().apply {
        set("agentId", 20)
    }
    private val agentRepository = FakeAgentRepository()
    private val driveRepository = FakeDriveRepository()
    private lateinit var viewModel: AgentDetailViewModel

    @BeforeTest
    fun setup() {
        viewModel = AgentDetailViewModel(savedStateHandle, agentRepository, driveRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentDetail, stubAgentDetailResponse)
    }
}