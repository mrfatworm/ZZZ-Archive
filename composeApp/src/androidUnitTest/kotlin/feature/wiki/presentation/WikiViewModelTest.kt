/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsList
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooList
import feature.drive.data.database.stubDrivesListItemEntity
import feature.drive.domain.DrivesListUseCase
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
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
        coEvery { agentsListUseCase.invoke() } returns flowOf(stubAgentsList)
        coEvery { wEnginesListUseCase.invoke() } returns flowOf(stubWEnginesList)
        coEvery { bangbooListUseCase.invoke() } returns flowOf(stubBangbooList)
        coEvery { drivesListUseCase.invoke() } returns flowOf(listOf(stubDrivesListItemEntity))
        viewModel = WikiViewModel(
            agentsListUseCase, wEnginesListUseCase, bangbooListUseCase, drivesListUseCase
        )
    }

    @Test
    fun `Init Data Success`() {
        viewModel.uiState.onEach { state ->
            assertEquals(stubAgentsList, state.agentsList)
            assertEquals(stubWEnginesList, state.wEnginesList)
            assertEquals(stubBangbooList, state.bangbooList)
            assertEquals(listOf(stubDrivesListItemEntity), state.drivesList)
        }
    }
}