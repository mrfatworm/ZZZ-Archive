/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.BangbooListItem
import feature.bangboo.model.BangbooListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult

class BangbooListViewModel(
    private val bangbooListUseCase: BangbooListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(BangbooListState())
    val uiState = _uiState.asStateFlow()
    private var _bangbooList = MutableStateFlow<UiResult<List<BangbooListItem>>>(UiResult.Loading)
    val bangbooList = _bangbooList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchBangbooList()
        }
    }

    private suspend fun fetchBangbooList() {
        _bangbooList.value = UiResult.Loading
        val result = bangbooListUseCase.invoke()
        _bangbooList.value = result.fold(onSuccess = { bangbooList ->
            _uiState.update {
                it.copy(
                    bangbooList = bangbooList, filteredBangbooList = bangbooList
                )
            }
            UiResult.Success(bangbooList)
        }, onFailure = { UiResult.Error(it.message ?: "Unknown Error") })
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

            BangbooListAction.OnRetry -> {
                viewModelScope.launch {
                    fetchBangbooList()
                }
            }

            BangbooListAction.OnBackClick -> {}
            is BangbooListAction.OnBangbooClick -> {}
        }
    }


    private fun filterBangbooList(
    ) {
        _uiState.update {
            it.copy(
                filteredBangbooList = bangbooListUseCase.filterBangbooList(
                    it.bangbooList, it.selectedRarity, it.selectedAttributes
                )
            )
        }
    }
}