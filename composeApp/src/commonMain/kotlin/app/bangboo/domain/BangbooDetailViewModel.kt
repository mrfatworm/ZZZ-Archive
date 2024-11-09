/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.bangboo.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bangboo.data.BangbooRepository
import app.bangboo.model.BangbooDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class BangbooDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val bangbooRepository: BangbooRepository
) : ViewModel() {
    private var bangbooId: Int = checkNotNull(savedStateHandle["bangbooId"])

    private var _uiState = MutableStateFlow(BangbooDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchBangbooDetail(bangbooId)
        }
    }

    private suspend fun fetchBangbooDetail(id: Int) {
        when (val result = bangbooRepository.getBangbooDetail(id)) {
            is ZzzResult.Success -> {
                _uiState.update {
                    it.copy(
                        bangbooDetail = result.data
                    )
                }
            }

            is ZzzResult.Error -> {
                println("get bangboo $id error: ${result.exception}")
            }
        }
    }
}