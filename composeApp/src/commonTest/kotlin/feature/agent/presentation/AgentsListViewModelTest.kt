/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import MainDispatcherTest
import database.UpdateDatabaseUseCase
import feature.agent.data.repository.FakeAgentRepository
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.stubAgentsList
import feature.cover.data.FakeCoverImageRepository
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

class AgentsListViewModelTest : MainDispatcherTest() {
    private val agentRepository = FakeAgentRepository()
    private val agentsListUseCase = AgentsListUseCase(agentRepository, FakeLanguageUseCase())
    private val updateDatabaseUseCase =
        UpdateDatabaseUseCase(FakeCoverImageRepository(), agentRepository)
    private val viewModel = AgentsListViewModel(agentsListUseCase, updateDatabaseUseCase)

    @Test
    fun `Init data success`() = runViewModelTest {
        val state = viewModel.uiState.first()
        assertEquals(stubAgentsList, state.agentsList)
        assertEquals(stubAgentsList, state.filteredAgentsList)
        assertEquals(2, state.factionsList.size)
        assertEquals(1, agentRepository.requestAndUpdateAgentsListDBCount)
    }

    @Test
    fun `Filter rarity S`() = runViewModelTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeRarityFilter(setOf(ZzzRarity.RARITY_S)))
        val state = viewModel.uiState.value
        assertEquals(listOf(3), state.filteredAgentsList.map { it.id })
    }

    @Test
    fun `Filter attribute Electric`() = runViewModelTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeAttributeFilter(setOf(AgentAttribute.Electric)))
        val state = viewModel.uiState.value
        assertEquals(listOf(4), state.filteredAgentsList.map { it.id })
    }

    @Test
    fun `Filter specialty Stun`() = runViewModelTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeSpecialtyFilter(setOf(AgentSpecialty.Stun)))
        val state = viewModel.uiState.value
        assertEquals(listOf(4), state.filteredAgentsList.map { it.id })
    }

    @Test
    fun `Filter faction`() = runViewModelTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeFactionFilter(1))
        val state = viewModel.uiState.value
        assertEquals(listOf(3, 4), state.filteredAgentsList.map { it.id })
    }

    @Test
    fun `Select the same faction twice THEN clear the faction filter`() = runViewModelTest {
        viewModel.uiState.first()
        viewModel.onAction(AgentsListAction.ChangeFactionFilter(1))
        viewModel.onAction(AgentsListAction.ChangeFactionFilter(1))
        val state = viewModel.uiState.value
        assertEquals(0, state.selectedFactionId)
        assertEquals(stubAgentsList, state.filteredAgentsList)
    }
}
