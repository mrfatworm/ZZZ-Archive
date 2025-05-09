/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentDetailUseCase
import feature.agent.model.AgentDetailState
import feature.drive.domain.DrivesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val agentDetailUseCase: AgentDetailUseCase,
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {
    private val agentId: Int = checkNotNull(savedStateHandle["agentId"])

    private var _uiState = MutableStateFlow(AgentDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsDetail(agentId)
            observeDrivesList()
        }
    }

    private suspend fun fetchAgentsDetail(id: Int) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        val result = agentDetailUseCase.invoke(id)
        result.fold(onSuccess = { agentDetail ->
            _uiState.update {
                it.copy(agentDetail = agentDetail, isLoading = false)
            }
        }, onFailure = { throwable ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = throwable.message ?: "Unknown error: AgentId = $id"
                )
            }
        })
    }

    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _uiState.update {
                it.copy(drivesList = drivesList)
            }
        }
    }

    fun onAction(action: AgentDetailAction) {
        when (action) {
            is AgentDetailAction.ClickWEngine -> {}
            AgentDetailAction.ClickBack -> {}
            AgentDetailAction.Retry -> {
                viewModelScope.launch {
                    fetchAgentsDetail(agentId)
                }
            }
        }
    }
}