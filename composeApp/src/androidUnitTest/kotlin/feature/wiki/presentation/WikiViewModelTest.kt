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
import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesListResponse
import feature.wiki.domain.WikiViewModel
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
    private var wEnginesListUseCase = mockk<WEnginesListUseCase>()
    private var bangbooListUseCase = mockk<BangbooListUseCase>()
    private var driveRepository = FakeDriveRepository()
    private lateinit var viewModel: WikiViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsListResponse.agents)
        coEvery { wEnginesListUseCase.invoke() } returns Result.success(stubWEnginesListResponse.wEngines)
        coEvery { bangbooListUseCase.invoke() } returns Result.success(stubBangbooListResponse.bangboo)
        viewModel = WikiViewModel(
            agentsListUseCase,
            wEnginesListUseCase,
            bangbooListUseCase,
            driveRepository,
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsListResponse.agents)
        assertEquals(state.wEnginesList, stubWEnginesListResponse.wEngines)
        assertEquals(state.bangbooList, stubBangbooListResponse.bangboo)
        assertEquals(state.drivesList, stubDrivesListResponse.getDrivesNewToOld())
    }
}