/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation


import MainDispatcherRule
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.model.stubMyAgentsList
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MyAgentsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val hoYoLabAgeUseCase = mockk<HoYoLabAgentUseCase>()
    private lateinit var viewModel: MyAgentsListViewModel

    @BeforeTest
    fun setup() {
        coEvery {
            hoYoLabAgeUseCase.getAgentsList()
        } returns Result.success(stubMyAgentsList)

        viewModel = MyAgentsListViewModel(hoYoLabAgeUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubMyAgentsList)
    }
}