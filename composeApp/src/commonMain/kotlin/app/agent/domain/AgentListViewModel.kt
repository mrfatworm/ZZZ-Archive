/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.agent.data.AgentRepository
import app.agent.model.AgentsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class AgentListViewModel(
    private val agentRepository: AgentRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(AgentsListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsList()
        }
    }

    private suspend fun fetchAgentsList() {
        when (val result = agentRepository.getAgentsList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(agentsList = result.data.getAgentsNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get agents error: ${result.exception}")
            }
        }
    }
}