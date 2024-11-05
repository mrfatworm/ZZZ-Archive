/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.agent.data.AgentRepository
import app.agent.model.AgentDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class AgentDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val agentRepository: AgentRepository,
) : ViewModel() {
    private var agentId: Int = checkNotNull(savedStateHandle["agentId"])

    private var _uiState = MutableStateFlow(AgentDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsDetail(agentId)
        }
    }

    private suspend fun fetchAgentsDetail(id: Int) {
        when (val result = agentRepository.getAgentDetail(id)) {
            is ZzzResult.Success -> {
                _uiState.update {
                    it.copy(
                        agentDetail = result.data
                    )
                }
            }

            is ZzzResult.Error -> {
                println("get agent $id error: ${result.exception}")
            }
        }
    }
}