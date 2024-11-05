/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wengine.data.WEngineRepository
import app.wengine.model.WEngineDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class WEngineDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val wEngineRepository: WEngineRepository,
) : ViewModel() {
    private var wEngineId: Int = checkNotNull(savedStateHandle["wEngineId"])

    private var _uiState = MutableStateFlow(WEngineDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchWEngineDetail(wEngineId)
        }
    }

    private suspend fun fetchWEngineDetail(id: Int) {
        when (val result = wEngineRepository.getWEngineDetail(id)) {
            is ZzzResult.Success -> {
                _uiState.update {
                    it.copy(
                        wEngineDetail = result.data
                    )
                }
            }

            is ZzzResult.Error -> {
                println("get w-engine $id error: ${result.exception}")
            }
        }
    }
}