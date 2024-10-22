/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wengine.data.WEngineRepository
import app.wengine.model.WEngineListItem
import app.wengine.model.WEnginesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.AgentSpecialty
import utils.ZzzRarity
import utils.ZzzResult

class WEnginesListViewModel(
    private val wEngineRepository: WEngineRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(WEnginesListState())
    val uiState = _uiState.asStateFlow()
    private var unfilteredWEnginesList: MutableStateFlow<List<WEngineListItem>> =
        MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            fetchWEnginesList()
        }
    }

    private suspend fun fetchWEnginesList() {
        when (val result = wEngineRepository.getWEnginesList()) {
            is ZzzResult.Success -> {
                unfilteredWEnginesList.update {
                    result.data.getWEnginesNewToOld()
                }
                filterWEnginesList()
            }

            is ZzzResult.Error -> {
                println("get agents error: ${result.exception}")
            }
        }
    }

    fun rarityFilterChanged(
        rarities: Set<ZzzRarity>
    ) {
        _uiState.update {
            it.copy(selectedRarity = rarities)
        }
        filterWEnginesList()
    }

    fun specialtyFilterChanged(
        specialties: Set<AgentSpecialty>
    ) {
        _uiState.update {
            it.copy(selectedSpecialties = specialties)
        }
        filterWEnginesList()
    }

    private fun filterWEnginesList(
    ) {
        val filteredAgents = unfilteredWEnginesList.value.filter { agent ->
            val matchRarity =
                uiState.value.selectedRarity.isEmpty() || uiState.value.selectedRarity.any { it.level == agent.rarity }
            val matchSpecialty =
                uiState.value.selectedSpecialties.isEmpty() || uiState.value.selectedSpecialties.any { it.name.lowercase() == agent.specialty }

            matchRarity && matchSpecialty
        }
        _uiState.update {
            it.copy(
                wEnginesList = filteredAgents
            )
        }
    }
}