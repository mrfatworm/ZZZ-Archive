/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.domain


import MainDispatcherRule
import feature.agent.data.FakeAgentRepository
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import feature.wengine.data.FakeWEngineRepository
import feature.wengine.model.stubWEnginesListResponse
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WikiViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var agentRepository = FakeAgentRepository()
    private var wEngineRepository = FakeWEngineRepository()
    private var bangbooRepository = FakeBangbooRepository()
    private var driveRepository = FakeDriveRepository()
    private lateinit var viewModel: WikiViewModel

    @BeforeTest
    fun setup() {
        viewModel = WikiViewModel(
            agentRepository,
            wEngineRepository,
            bangbooRepository,
            driveRepository,
        )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsListResponse.getAgentsNewToOld())
        assertEquals(state.wEnginesList, stubWEnginesListResponse.getWEnginesNewToOld())
        assertEquals(state.bangbooList, stubBangbooListResponse.getBangbooNewToOld())
        assertEquals(state.drivesList, stubDrivesListResponse.getDrivesNewToOld())
    }
}