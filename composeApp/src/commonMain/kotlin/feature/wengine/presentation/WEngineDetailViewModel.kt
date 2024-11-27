/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.wengine.domain.WEngineDetailUseCase
import feature.wengine.model.WEngineDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WEngineDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val wEngineDetailUseCase: WEngineDetailUseCase
) : ViewModel() {
    private val wEngineId: Int = checkNotNull(savedStateHandle["wEngineId"])

    private var _uiState = MutableStateFlow(WEngineDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeWEngineDetail(wEngineId)
        }
    }

    private suspend fun observeWEngineDetail(id: Int) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        wEngineDetailUseCase.invoke(id).fold(
            onSuccess = { wEngine ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        wEngineDetail = wEngine
                    )
                }
            }, onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false, error = throwable.message ?: "Unknown error"
                    )
                }
            }
        )
    }

    fun onAction(action: WEngineDetailAction) {
        when (action) {
            WEngineDetailAction.OnBackClick -> {}
            WEngineDetailAction.OnRetry -> {
                viewModelScope.launch {
                    observeWEngineDetail(wEngineId)
                }
            }
        }
    }
}