/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation


import MainDispatcherRule
import androidx.lifecycle.SavedStateHandle
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.model.my_agent_detail.stubMyAgentDetailListItem
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MyAgentDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val hoYoLabAgentUseCase = mockk<HoYoLabAgentUseCase>()
    private val savedStateHandle = SavedStateHandle().apply {
        set("agentId", 1)
    }
    private lateinit var viewModel: MyAgentDetailViewModel

    @BeforeTest
    fun setup() {
        coEvery {
            hoYoLabAgentUseCase.getAgentDetail(any())
        } returns Result.success(stubMyAgentDetailListItem)

        viewModel = MyAgentDetailViewModel(savedStateHandle, hoYoLabAgentUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentDetail, stubMyAgentDetailListItem)
    }
}