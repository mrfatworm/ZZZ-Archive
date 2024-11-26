/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.AgentsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentsListViewModel(
    private val agentsListUseCase: AgentsListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(AgentsListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeAgentsList()
        }
    }

    private suspend fun observeAgentsList() {
        agentsListUseCase.invoke().collect { agentsList ->
            _uiState.update {
                it.copy(
                    agentsList = agentsList,
                    filteredAgentsList = agentsList,
                    factionsList = agentsListUseCase.getFactionsList(agentsList)
                )
            }
        }
    }

    fun onAction(action: AgentsListAction) {
        when (action) {
            is AgentsListAction.OnRarityFilterChanged -> {
                _uiState.update {
                    it.copy(selectedRarity = action.rarities)
                }
                filterAgentsList()
            }

            is AgentsListAction.OnAttributeFilterChanged -> {
                _uiState.update {
                    it.copy(selectedAttributes = action.attributes)
                }
                filterAgentsList()
            }

            is AgentsListAction.OnSpecialtyFilterChanged -> {
                _uiState.update {
                    it.copy(selectedSpecialties = action.specialties)
                }
                filterAgentsList()
            }

            is AgentsListAction.OnFactionFilterChanged -> {
                val factionId = action.factionId
                _uiState.update {
                    it.copy(selectedFactionId = if (it.selectedFactionId == factionId) 0 else factionId)
                }
                filterAgentsList()
            }

            is AgentsListAction.OnAgentClick -> {}

            AgentsListAction.OnBackClick -> {}
        }
    }

    private fun filterAgentsList() {
        val filteredAgents = agentsListUseCase.filterAgentsList(
            uiState.value.agentsList,
            uiState.value.selectedRarity,
            uiState.value.selectedAttributes,
            uiState.value.selectedSpecialties,
            uiState.value.selectedFactionId
        )
        _uiState.update {
            it.copy(
                filteredAgentsList = filteredAgents
            )
        }
    }
}