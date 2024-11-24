/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.bangboo.domain.BangbooDetailUseCase
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult

class BangbooDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val bangbooDetailUseCase: BangbooDetailUseCase
) : ViewModel() {
    private var bangbooId: Int = checkNotNull(savedStateHandle["bangbooId"])

    private var _uiState = MutableStateFlow(BangbooDetailState())
    val uiState = _uiState.asStateFlow()
    private var _bangbooDetail = MutableStateFlow<UiResult<BangbooDetailResponse>>(UiResult.Loading)
    val bangbooDetail = _bangbooDetail.asStateFlow()

    init {
        viewModelScope.launch {
            fetchBangbooDetail(bangbooId)
        }
    }

    private suspend fun fetchBangbooDetail(id: Int) {
        _bangbooDetail.value = UiResult.Loading
        val result = bangbooDetailUseCase.invoke(id)
        _bangbooDetail.value = result.fold(
            onSuccess = { bangbooDetail ->
                _uiState.update {
                    it.copy(bangbooDetail = bangbooDetail)
                }
                UiResult.Success(bangbooDetail)
            },
            onFailure = { UiResult.Error(it.message ?: "Unknown Error") }
        )
    }

    fun onAction(action: BangbooDetailAction) {
        when (action) {
            BangbooDetailAction.OnBackClick -> TODO()
            BangbooDetailAction.OnRetry -> {
                viewModelScope.launch {
                    fetchBangbooDetail(bangbooId)
                }
            }
        }
    }
}