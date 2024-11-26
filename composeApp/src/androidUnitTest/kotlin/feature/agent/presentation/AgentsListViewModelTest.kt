/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation


import MainDispatcherRule
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.Faction
import feature.agent.model.stubAgentsList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
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

    private val agentsListUseCase = mockk<AgentsListUseCase>()
    private lateinit var viewModel: AgentsListViewModel

    @BeforeTest
    fun setup() {
        coEvery { agentsListUseCase.invoke() } returns Result.success(stubAgentsList)
        every { agentsListUseCase.getFactionsList(any()) } returns listOf(Faction(1), Faction(2))
        every {
            agentsListUseCase.filterAgentsList(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns listOf(stubAgentsList.first())
        viewModel = AgentsListViewModel(agentsListUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.agentsList, stubAgentsList)
        assertEquals(state.filteredAgentsList, stubAgentsList)
        assertEquals(state.factionsList.size, 2)
    }

    @Test
    fun `Filter Rarity S Success`() {
        viewModel.onAction(AgentsListAction.OnRarityFilterChanged(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredAgentsList.first().id, 3) // First agent: Nekomiya
        assertEquals(state.filteredAgentsList.size, 1)
    }

    @Test
    fun `Filter Attribute Electric Success`() {
        viewModel.onAction(AgentsListAction.OnAttributeFilterChanged(setOf(AgentAttribute.Electric)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredAgentsList.first().id, 3) // First agent: Nekomiya
        assertEquals(state.filteredAgentsList.size, 1)
    }

    @Test
    fun `Filter Specialty Stun Success`() {
        viewModel.onAction(AgentsListAction.OnSpecialtyFilterChanged(setOf(AgentSpecialty.Stun)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredAgentsList.first().id, 3) // First agent: Nekomiya
        assertEquals(state.filteredAgentsList.size, 1)
    }

    @Test
    fun `Filter Faction Success`() {
        viewModel.onAction(AgentsListAction.OnFactionFilterChanged((1)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredAgentsList.first().id, 3) // First agent: Nekomiya
        assertEquals(state.filteredAgentsList.size, 1)
    }
}