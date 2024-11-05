/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wiki.domain


import MainDispatcherRule
import app.agent.data.AgentRepository
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import app.bangboo.data.BangbooRepository
import app.bangboo.data.FakeBangbooRepository
import app.bangboo.model.stubBangbooListResponse
import app.drive.data.DriveRepository
import app.drive.data.FakeDriveRepository
import app.drive.model.stubDrivesListResponse
import app.wengine.data.FakeWEngineRepository
import app.wengine.data.WEngineRepository
import app.wengine.model.stubWEnginesListResponse
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WikiViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var agentRepository: AgentRepository
    private lateinit var wEngineRepository: WEngineRepository
    private lateinit var bangbooRepository: BangbooRepository
    private lateinit var driveRepository: DriveRepository
    private lateinit var viewModel: WikiViewModel

    @BeforeTest
    fun setup() {
        agentRepository = FakeAgentRepository()
        wEngineRepository = FakeWEngineRepository()
        bangbooRepository = FakeBangbooRepository()
        driveRepository = FakeDriveRepository()
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