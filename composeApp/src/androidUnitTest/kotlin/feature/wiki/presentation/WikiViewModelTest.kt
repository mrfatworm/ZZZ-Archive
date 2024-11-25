/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooListResponse
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.stubDrivesListResponse
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesListResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WikiViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val agentsListUseCase = mockk<AgentsListUseCase>()
    private val wEnginesListUseCase = mockk<WEnginesListUseCase>()
    private val bangbooListUseCase = mockk<BangbooListUseCase>()
    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private lateinit var viewModel: WikiViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsListResponse.agents)
        coEvery { wEnginesListUseCase.invoke() } returns Result.success(stubWEnginesListResponse.wEngines)
        coEvery { bangbooListUseCase.invoke() } returns Result.success(stubBangbooListResponse.bangboo)
        coEvery { drivesListUseCase.invoke() } returns Result.success(stubDrivesListResponse.drives)
        viewModel = WikiViewModel(
            agentsListUseCase,
            wEnginesListUseCase,
            bangbooListUseCase,
            drivesListUseCase
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsListResponse.agents)
        assertEquals(state.wEnginesList, stubWEnginesListResponse.wEngines)
        assertEquals(state.bangbooList, stubBangbooListResponse.bangboo)
        assertEquals(state.drivesList, stubDrivesListResponse.drives)
    }
}