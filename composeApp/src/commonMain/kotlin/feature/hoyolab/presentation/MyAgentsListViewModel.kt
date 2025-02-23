package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.model.MyAgentsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyAgentsListViewModel(private val hoYoLabAgentUseCase: HoYoLabAgentUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MyAgentsListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateMyAgentFromHoYoLab()
        }
    }

    private suspend fun updateMyAgentFromHoYoLab() {
        hoYoLabAgentUseCase.getAgentsList().fold(
            onSuccess = {
                _uiState.update { state ->
                    state.copy(agentsList = it)
                }
            },
            onFailure = {
                _uiState.update { state ->
                    println("Get Hoyolab agent fail")
                    state.copy(errorMessage = it.message ?: "Unknown error")
                }
            })
    }
}