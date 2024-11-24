/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.agent.domain.AgentDetailUseCase
import feature.agent.model.stubAgentDetailResponse
import feature.drive.data.FakeDriveRepository
import io.mockk.coEvery
import io.mockk.mockk
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
    private val agentDetailUseCase = mockk<AgentDetailUseCase>()
    private val driveRepository = FakeDriveRepository()
    private lateinit var viewModel: AgentDetailViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentDetailUseCase.invoke(any()) } returns Result.success(stubAgentDetailResponse)
        viewModel = AgentDetailViewModel(savedStateHandle, agentDetailUseCase, driveRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentDetail, stubAgentDetailResponse)
    }
}