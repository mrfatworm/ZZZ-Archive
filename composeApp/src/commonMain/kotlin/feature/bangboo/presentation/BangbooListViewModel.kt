/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.BangbooListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BangbooListViewModel(
    private val bangbooListUseCase: BangbooListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(BangbooListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeBangbooList()
        }
    }

    private suspend fun observeBangbooList() {
        bangbooListUseCase.invoke().collect { bangbooList ->
            _uiState.update {
                it.copy(
                    bangbooList = bangbooList,
                    filteredBangbooList = bangbooList
                )
            }
        }
    }

    fun onAction(action: BangbooListAction) {
        when (action) {
            is BangbooListAction.OnAttributeFilterChanged -> {
                _uiState.update {
                    it.copy(selectedAttributes = action.attributes)
                }
                filterBangbooList()
            }

            is BangbooListAction.OnRarityFilterChanged -> {
                _uiState.update {
                    it.copy(selectedRarity = action.rarities)
                }
                filterBangbooList()
            }

            BangbooListAction.OnBackClick -> {}
            is BangbooListAction.OnBangbooClick -> {}
        }
    }


    private fun filterBangbooList() {
        _uiState.update {
            it.copy(
                filteredBangbooList = bangbooListUseCase.filterBangbooList(
                    it.bangbooList, it.selectedRarity, it.selectedAttributes
                )
            )
        }
    }
}