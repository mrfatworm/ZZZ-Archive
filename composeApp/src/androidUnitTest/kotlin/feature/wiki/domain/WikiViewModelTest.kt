/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.domain


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import feature.wengine.data.FakeWEngineRepository
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
    private var wEngineRepository = FakeWEngineRepository()
    private var bangbooRepository = FakeBangbooRepository()
    private var driveRepository = FakeDriveRepository()
    private lateinit var viewModel: WikiViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsListResponse.agents)
        viewModel = WikiViewModel(
            agentsListUseCase,
            wEngineRepository,
            bangbooRepository,
            driveRepository,
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsListResponse.agents)
        assertEquals(state.wEnginesList, stubWEnginesListResponse.getWEnginesNewToOld())
        assertEquals(state.bangbooList, stubBangbooListResponse.getBangbooNewToOld())
        assertEquals(state.drivesList, stubDrivesListResponse.getDrivesNewToOld())
    }
}