/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.wengine.domain.WEngineDetailUseCase
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEngineDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult

class WEngineDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val wEngineDetailUseCase: WEngineDetailUseCase
) : ViewModel() {
    private val wEngineId: Int = checkNotNull(savedStateHandle["wEngineId"])

    private var _uiState = MutableStateFlow(WEngineDetailState())
    val uiState = _uiState.asStateFlow()
    private var _wEngineDetail = MutableStateFlow<UiResult<WEngineDetailResponse>>(UiResult.Loading)
    val wEngineDetail = _wEngineDetail.asStateFlow()

    init {
        viewModelScope.launch {
            fetchWEngineDetail(wEngineId)
        }
    }

    private suspend fun fetchWEngineDetail(id: Int) {
        _wEngineDetail.value = UiResult.Loading
        val result = wEngineDetailUseCase.invoke(id)
        _wEngineDetail.value = result.fold(
            onSuccess = { wEngine ->
                _uiState.update {
                    it.copy(
                        wEngineDetail = wEngine
                    )
                }
                UiResult.Success(wEngine)
            },
            onFailure = {
                UiResult.Error(it.message ?: "Unknown error")
            }
        )
    }

    fun onAction(action: WEngineDetailAction) {
        when (action) {
            WEngineDetailAction.OnBackClick -> {}
            WEngineDetailAction.OnRetry -> {
                viewModelScope.launch {
                    fetchWEngineDetail(wEngineId)
                }
            }
        }
    }
}