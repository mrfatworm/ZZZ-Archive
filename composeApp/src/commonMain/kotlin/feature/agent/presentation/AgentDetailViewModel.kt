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
import feature.drive.data.DriveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult
import utils.ZzzResult

class AgentDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val agentDetailUseCase: AgentDetailUseCase,
    private val driveRepository: DriveRepository
) : ViewModel() {
    private var agentId: Int = checkNotNull(savedStateHandle["agentId"])

    private var _uiState = MutableStateFlow(AgentDetailState())
    val uiState = _uiState.asStateFlow()

    private var _agentDetailState = MutableStateFlow<UiResult<AgentDetailState>>(UiResult.Loading)
    val agentDetailState = _agentDetailState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsDetail(agentId)
            fetchDrivesList()
        }
    }

    private suspend fun fetchAgentsDetail(id: Int) {
        _agentDetailState.value = UiResult.Loading
        val result = agentDetailUseCase.invoke(id)
        _agentDetailState.value = result.fold(onSuccess = { agentDetail ->
            _uiState.update {
                it.copy(agentDetail = agentDetail)
            }
            UiResult.Success(AgentDetailState(agentDetail))
        }, onFailure = {
            UiResult.Error(it.message ?: "Unknown error: AgentId = $id")
        })
    }

    private suspend fun fetchDrivesList() {
        when (val result = driveRepository.getDrivesList()) {
            is ZzzResult.Success -> {
                _uiState.update {
                    it.copy(
                        drivesList = result.data.drives
                    )
                }
            }

            is ZzzResult.Error -> {
                println("get drives list error: ${result.exception}")
            }
        }
    }

    fun onAction(action: AgentDetailAction) {
        when (action) {
            is AgentDetailAction.OnWEngineClick -> {}
            AgentDetailAction.OnBackClick -> {}
            AgentDetailAction.OnRetry -> {
                viewModelScope.launch {
                    fetchAgentsDetail(agentId)
                }
            }
        }
    }
}