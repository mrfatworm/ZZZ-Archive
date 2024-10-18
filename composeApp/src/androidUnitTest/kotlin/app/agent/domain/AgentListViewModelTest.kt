/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.domain


import MainDispatcherRule
import app.agent.data.AgentRepository
import app.agent.data.FakeAgentRepository
import app.agent.model.stubAgentsListResponse
import org.junit.Rule
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AgentListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var agentRepository: AgentRepository
    private lateinit var viewModel: AgentListViewModel

    @BeforeTest
    fun setup() {
        agentRepository = FakeAgentRepository()
        viewModel = AgentListViewModel(agentRepository)
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