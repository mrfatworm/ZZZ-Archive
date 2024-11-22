/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain


import MainDispatcherRule
import feature.agent.data.FakeAgentRepository
import feature.agent.model.stubAgentsListResponse
import org.junit.Rule
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AgentsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val agentRepository = FakeAgentRepository()
    private lateinit var viewModel: AgentsListViewModel

    @BeforeTest
    fun setup() {
        viewModel = AgentsListViewModel(agentRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsListResponse.getAgentsNewToOld())
        assertEquals(state.factionsList.size, 2)
    }

    @Test
    fun `Filter Rarity S Success`() {
        viewModel.rarityFilterChanged(setOf(ZzzRarity.RANK_S))
        val state = viewModel.uiState.value
        assertEquals(state.agentsList.first().id, 3) // Nekomiya
        assertEquals(state.agentsList.size, 1)
    }

    @Test
    fun `Filter Attribute Electric Success`() {
        viewModel.attributeFilterChanged(setOf(AgentAttribute.Electric))
        val state = viewModel.uiState.value
        assertEquals(state.agentsList.first().id, 4) // Anby
        assertEquals(state.agentsList.size, 1)
    }

    @Test
    fun `Filter Specialty Stun Success`() {
        viewModel.specialtyFilterChanged(setOf(AgentSpecialty.Stun))
        val state = viewModel.uiState.value
        assertEquals(state.agentsList.first().id, 4) // Anby
        assertEquals(state.agentsList.size, 1)
    }

    @Test
    fun `Filter Faction Success`() {
        viewModel.factionFilterChanged(1)
        val state = viewModel.uiState.value
        assertEquals(state.agentsList.size, 2)
    }
}