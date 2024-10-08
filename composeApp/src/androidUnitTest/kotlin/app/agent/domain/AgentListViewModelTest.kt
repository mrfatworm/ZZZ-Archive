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
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
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

    @Test
    fun `Filter Rarity S Success`() {
        viewModel.rarityFilterChanged(setOf(ZzzRarity.RANK_S))
        val state = viewModel.uiState.value
        assertThat(state.agentsList.first().id).isEqualTo(3) // Nekomiya
        assertThat(state.agentsList.size).isEqualTo(1)
    }

    @Test
    fun `Filter Attribute Electric Success`() {
        viewModel.attributeFilterChanged(setOf(AgentAttribute.Electric))
        val state = viewModel.uiState.value
        assertThat(state.agentsList.first().id).isEqualTo(4) // Anby
        assertThat(state.agentsList.size).isEqualTo(1)
    }

    @Test
    fun `Filter Specialty Stun Success`() {
        viewModel.specialtyFilterChanged(setOf(AgentSpecialty.Stun))
        val state = viewModel.uiState.value
        assertThat(state.agentsList.first().id).isEqualTo(4) // Anby
        assertThat(state.agentsList.size).isEqualTo(1)
    }
}