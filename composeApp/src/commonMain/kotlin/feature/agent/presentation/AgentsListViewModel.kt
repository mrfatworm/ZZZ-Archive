/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.UpdateDatabaseUseCase
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.AgentsListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentsListViewModel(
    private val agentsListUseCase: AgentsListUseCase,
    private val updateDatabaseUseCase: UpdateDatabaseUseCase
) : ViewModel() {
    private var agentsListJob: Job? = null

    private var _uiState = MutableStateFlow(AgentsListState())
    val uiState = _uiState
        .onStart {
            updateAgentsList()
            observeAgentsList()
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(15000L),
            initialValue = _uiState.value
        )

    private fun updateAgentsList() {
        viewModelScope.launch {
            updateDatabaseUseCase.updateAgentsList()
        }
    }

    private fun observeAgentsList() {
        agentsListJob?.cancel()
        agentsListJob =
            viewModelScope.launch {
                agentsListUseCase.invoke().collect { agentsList ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            agentsList = agentsList,
                            highlightAgentsList = agentsList.filter { it.isHighlight },
                            filteredAgentsList = agentsList.filterNot { it.isHighlight },
                            factionsList = agentsListUseCase.getFactionsList(agentsList)
                        )
                    }
                }
            }
    }

    fun onAction(action: AgentsListAction) {
        when (action) {
            is AgentsListAction.ChangeRarityFilter -> {
                _uiState.update {
                    it.copy(selectedRarity = action.rarities)
                }
                filterAgentsList()
            }

            is AgentsListAction.ChangeAttributeFilter -> {
                _uiState.update {
                    it.copy(selectedAttributes = action.attributes)
                }
                filterAgentsList()
            }

            is AgentsListAction.ChangeSpecialtyFilter -> {
                _uiState.update {
                    it.copy(selectedSpecialties = action.specialties)
                }
                filterAgentsList()
            }

            is AgentsListAction.ChangeFactionFilter -> {
                val factionId = action.factionId
                _uiState.update {
                    it.copy(selectedFactionId = if (it.selectedFactionId == factionId) 0 else factionId)
                }
                filterAgentsList()
            }

            is AgentsListAction.ClearFilter -> {
                _uiState.update {
                    it.copy(
                        selectedRarity = emptySet(),
                        selectedAttributes = emptySet(),
                        selectedSpecialties = emptySet(),
                        selectedFactionId = 0
                    )
                }
                filterAgentsList()
            }

            is AgentsListAction.ClickAgent -> {}

            AgentsListAction.ClickBack -> {}

            is AgentsListAction.ClickGallery -> {}
        }
    }

    private fun filterAgentsList() {
        val filteredAgents =
            agentsListUseCase.filterAgentsList(
                uiState.value.agentsList,
                uiState.value.selectedRarity,
                uiState.value.selectedAttributes,
                uiState.value.selectedSpecialties,
                uiState.value.selectedFactionId
            )
        val isHighlightAgents = filteredAgents.filter { it.isHighlight }
        val nonHighlightAgents = filteredAgents.filterNot { it.isHighlight }
        _uiState.update {
            it.copy(
                highlightAgentsList = isHighlightAgents,
                filteredAgentsList = nonHighlightAgents
            )
        }
    }
}
