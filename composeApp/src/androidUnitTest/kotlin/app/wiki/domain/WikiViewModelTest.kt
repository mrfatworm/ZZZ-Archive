/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wiki.domain


import MainDispatcherRule
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import app.bangboo.data.FakeBangbooRepository
import app.bangboo.model.stubBangbooListResponse
import app.drive.data.FakeDriveRepository
import app.drive.model.stubDrivesListResponse
import app.wengine.data.FakeWEngineRepository
import app.wengine.model.stubWEnginesListResponse
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