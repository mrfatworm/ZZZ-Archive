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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
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
        coEvery { agentsListUseCase.invoke() } returns flowOf(stubAgentsList)
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
    fun `Init data success`() = runTest {
        val state = viewModel.uiState.first()
        assertEquals(stubAgentsList, state.agentsList)
        assertEquals(stubAgentsList, state.filteredAgentsList)
        assertEquals(2, state.factionsList.size)
    }

    @Test
    fun `Filter rarity S`() = runTest {
        viewModel.onAction(AgentsListAction.ChangeRarityFilter(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter attribute Electric`() = runTest {
        viewModel.onAction(AgentsListAction.ChangeAttributeFilter(setOf(AgentAttribute.Electric)))
        val state = viewModel.uiState.value
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter specialty Stun`() = runTest {
        viewModel.onAction(AgentsListAction.ChangeSpecialtyFilter(setOf(AgentSpecialty.Stun)))
        val state = viewModel.uiState.first()
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter faction`() = runTest {
        viewModel.onAction(AgentsListAction.ChangeFactionFilter((1)))
        val state = viewModel.uiState.first()
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }
}