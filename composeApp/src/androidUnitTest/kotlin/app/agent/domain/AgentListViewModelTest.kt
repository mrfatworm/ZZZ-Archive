/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.domain


import MainDispatcherRule
import app.agent.data.AgentRepository
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import app.home.data.FakeImageBannerRepository
import app.home.data.ImageBannerRepository
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test

class AgentListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var imageBannerRepository: ImageBannerRepository
    private lateinit var agentRepository: AgentRepository
    private lateinit var viewModel: AgentListViewModel

    @BeforeTest
    fun setup() {
        imageBannerRepository = FakeImageBannerRepository()
        agentRepository = FakeAgentRepository()
        viewModel = AgentListViewModel(agentRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertThat(state.agentsList).isEqualTo(stubAgentsListResponse.getAgentsNewToOld())
    }
}