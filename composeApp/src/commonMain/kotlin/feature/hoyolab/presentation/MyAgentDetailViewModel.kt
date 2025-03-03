package feature.hoyolab.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyAgentDetailViewModel(
    savedStateHandle: SavedStateHandle, private val hoYoLabAgentUseCase: HoYoLabAgentUseCase
) : ViewModel() {
    private val agentId: Int = checkNotNull(savedStateHandle["agentId"])

    private val _uiState = MutableStateFlow(MyAgentDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateMyAgentDetailFromHoYoLab()
        }
    }

    private suspend fun updateMyAgentDetailFromHoYoLab() {
        hoYoLabAgentUseCase.getAgentDetail(agentId).fold(onSuccess = {
            _uiState.update { state ->
                state.copy(agentDetail = it)
            }
        }, onFailure = {
            _uiState.update { state ->
                state.copy(errorMessage = it.message ?: "Unknown error")
            }
        })
    }
}