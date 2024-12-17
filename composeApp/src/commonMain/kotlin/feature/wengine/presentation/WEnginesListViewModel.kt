/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.WEnginesListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WEnginesListViewModel(
    private val wEnginesListUseCase: WEnginesListUseCase
) : ViewModel() {

    private var wEnginesListJob: Job? = null

    private var _uiState = MutableStateFlow(WEnginesListState())
    val uiState = _uiState.asStateFlow()

    init {
        observeWEnginesList()
    }

    fun onAction(action: WEnginesListAction) {
        when (action) {
            is WEnginesListAction.ChangeRarityFilter -> {
                _uiState.update {
                    it.copy(selectedRarity = action.rarities)
                }
                filterWEnginesList()
            }

            is WEnginesListAction.ChangeSpecialtyFilter -> {
                _uiState.update {
                    it.copy(selectedSpecialties = action.specialties)
                }
                filterWEnginesList()
            }

            is WEnginesListAction.ClickWEngine -> {}
            WEnginesListAction.ClickBack -> {}
        }
    }

    private fun observeWEnginesList() {
        wEnginesListJob?.cancel()
        wEnginesListJob = viewModelScope.launch {
            wEnginesListUseCase.invoke().collect { wEnginesList ->
                _uiState.update {
                    it.copy(wEnginesList = wEnginesList, filteredWEnginesList = wEnginesList)
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