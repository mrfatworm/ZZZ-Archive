/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.data.AgentRepository
import feature.agent.model.AgentListItem
import feature.agent.model.AgentsListState
import feature.agent.model.Faction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import utils.ZzzResult

class AgentsListViewModel(
    private val agentRepository: AgentRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(AgentsListState())
    val uiState = _uiState.asStateFlow()
    private var unfilteredAgentsList: MutableStateFlow<List<AgentListItem>> =
        MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            fetchAgentsList()
        }
    }

    private suspend fun fetchAgentsList() {
        when (val result = agentRepository.getAgentsList()) {
            is ZzzResult.Success -> {
                unfilteredAgentsList.update {
                    result.data.getAgentsNewToOld()
                }
                updateFactionsList()
                filterAgentsList()
            }

            is ZzzResult.Error -> {
                println("get agents error: ${result.exception}")
            }
        }
    }

    private fun updateFactionsList() {
        val maxFactionId = unfilteredAgentsList.value.maxOfOrNull { it.factionId } ?: 0
        val factionsList = List(maxFactionId) { index -> Faction(index + 1) }
        _uiState.update { it.copy(factionsList = factionsList) }
    }

    fun rarityFilterChanged(
        rarities: Set<ZzzRarity>
    ) {
        _uiState.update {
            it.copy(selectedRarity = rarities)
        }
        filterAgentsList()
    }

    fun attributeFilterChanged(
        attributes: Set<AgentAttribute>
    ) {
        _uiState.update {
            it.copy(selectedAttributes = attributes)
        }
        filterAgentsList()
    }

    fun specialtyFilterChanged(
        specialties: Set<AgentSpecialty>
    ) {
        _uiState.update {
            it.copy(selectedSpecialties = specialties)
        }
        filterAgentsList()
    }

    fun factionFilterChanged(
        factionId: Int
    ) {
        _uiState.update {
            it.copy(selectedFactionId = if (it.selectedFactionId == factionId) 0 else factionId)
        }
        filterAgentsList()
    }


    private fun filterAgentsList(
    ) {
        val filteredAgents = unfilteredAgentsList.value.filter { agent ->
            val matchRarity =
                uiState.value.selectedRarity.isEmpty() || uiState.value.selectedRarity.any { it.level == agent.rarity }
            val matchAttribute =
                uiState.value.selectedAttributes.isEmpty() || uiState.value.selectedAttributes.any { it.name.lowercase() == agent.attribute }
            val matchSpecialty =
                uiState.value.selectedSpecialties.isEmpty() || uiState.value.selectedSpecialties.any { it.name.lowercase() == agent.specialty }
            val matchFaction =
                uiState.value.selectedFactionId == 0 || uiState.value.selectedFactionId == agent.factionId

            matchRarity && matchAttribute && matchSpecialty && matchFaction
        }
        _uiState.update {
            it.copy(
                agentsList = filteredAgents
            )
        }
    }
}