/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.WEngineListItem
import feature.wengine.model.WEnginesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult

class WEnginesListViewModel(
    private val wEnginesListUseCase: WEnginesListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(WEnginesListState())
    val uiState = _uiState.asStateFlow()
    private var _wEnginesList = MutableStateFlow<UiResult<List<WEngineListItem>>>(UiResult.Loading)
    val wEnginesList = _wEnginesList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchWEnginesList()
        }
    }

    private suspend fun fetchWEnginesList() {
        _wEnginesList.value = UiResult.Loading
        val result = wEnginesListUseCase.invoke()
        _wEnginesList.value = result.fold(
            onSuccess = { wEngines ->
                _uiState.update {
                    it.copy(
                        wEnginesList = wEngines,
                        filteredWEnginesList = wEngines
                    )
                }
                UiResult.Success(wEngines)
            },
            onFailure = {
                UiResult.Error(it.message ?: "Unknown error")
            }
        )
    }

    fun onAction(action: WEnginesListAction) {
        when (action) {
            is WEnginesListAction.OnRarityFilterChanged -> {
                _uiState.update {
                    it.copy(selectedRarity = action.rarities)
                }
                filterWEnginesList()
            }

            is WEnginesListAction.OnSpecialtyFilterChanged -> {
                _uiState.update {
                    it.copy(selectedSpecialties = action.specialties)
                }
                filterWEnginesList()
            }

            is WEnginesListAction.OnWEngineClick -> {}
            WEnginesListAction.OnBackClick -> {}
            WEnginesListAction.OnRetry -> {
                viewModelScope.launch {
                    fetchWEnginesList()
                }
            }
        }
    }

    private fun filterWEnginesList(
    ) {
        val filteredWEngines = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = uiState.value.wEnginesList,
            selectedRarities = uiState.value.selectedRarity,
            selectedSpecialties = uiState.value.selectedSpecialties
        )
        _uiState.update {
            it.copy(
                filteredWEnginesList = filteredWEngines
            )
        }
    }
}