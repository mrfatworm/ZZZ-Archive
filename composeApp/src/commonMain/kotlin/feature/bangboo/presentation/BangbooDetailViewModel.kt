/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.bangboo.domain.BangbooDetailUseCase
import feature.bangboo.model.BangbooDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BangbooDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val bangbooDetailUseCase: BangbooDetailUseCase
) : ViewModel() {
    private val bangbooId: Int = checkNotNull(savedStateHandle["bangbooId"])

    private var _uiState = MutableStateFlow(BangbooDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchBangbooDetail(bangbooId)
        }
    }

    fun onAction(action: BangbooDetailAction) {
        when (action) {
            BangbooDetailAction.ClickBack -> {}
            BangbooDetailAction.Retry -> {
                viewModelScope.launch {
                    fetchBangbooDetail(bangbooId)
                }
            }
        }
    }

    private suspend fun fetchBangbooDetail(id: Int) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        bangbooDetailUseCase.invoke(id).fold(
            onSuccess = { bangbooDetail ->
                _uiState.update {
                    it.copy(bangbooDetail = bangbooDetail)
                }
            }, onFailure = { throwable ->
                _uiState.update {
                    it.copy(isLoading = false, error = throwable.message ?: "Unknown Error")
                }
            }
        )
    }
}