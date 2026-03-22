/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import MainDispatcherRule
import database.UpdateDatabaseUseCase
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.Faction
import feature.agent.model.stubAgentsList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

class AgentsListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val agentsListUseCase = mockk<AgentsListUseCase>()
    private val updateDatabaseUseCase = mockk<UpdateDatabaseUseCase>()
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
        coEvery { updateDatabaseUseCase.updateAgentsList() } returns Unit
        viewModel = AgentsListViewModel(agentsListUseCase, updateDatabaseUseCase)
    }

    @Test
    fun `Init data success`() = runTest {
        val state = viewModel.uiState.first()
        assertEquals(stubAgentsList, state.agentsList)
        assertEquals(stubAgentsList, state.filteredAgentsList)
        assertEquals(2, state.factionsList.size)
        // Verify updateAgentsList is called
        coVerify { updateDatabaseUseCase.updateAgentsList() }
    }

    @Test
    fun `Filter rarity S`() = runTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeRarityFilter(setOf(ZzzRarity.RARITY_S)))
        val state = viewModel.uiState.value
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter attribute Electric`() = runTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeAttributeFilter(setOf(AgentAttribute.Electric)))
        val state = viewModel.uiState.value
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter specialty Stun`() = runTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeSpecialtyFilter(setOf(AgentSpecialty.Stun)))
        val state = viewModel.uiState.first()
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }

    @Test
    fun `Filter faction`() = runTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeFactionFilter((1)))
        val state = viewModel.uiState.first()
        assertEquals(3, state.filteredAgentsList.first().id) // First agent: Nekomiya
        assertEquals(1, state.filteredAgentsList.size)
    }
}
