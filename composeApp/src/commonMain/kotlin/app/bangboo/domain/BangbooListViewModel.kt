/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bangboo.data.BangbooRepository
import app.bangboo.model.BangbooListItem
import app.bangboo.model.BangbooListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.AgentAttribute
import utils.ZzzRarity
import utils.ZzzResult

class BangbooListViewModel(
    private val bangbooRepository: BangbooRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(BangbooListState())
    val uiState = _uiState.asStateFlow()
    private var unfilteredBangbooList: MutableStateFlow<List<BangbooListItem>> =
        MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            fetchAgentsList()
        }
    }

    private suspend fun fetchAgentsList() {
        when (val result = bangbooRepository.getBangbooList()) {
            is ZzzResult.Success -> {
                unfilteredBangbooList.update {
                    result.data.getBangbooNewToOld()
                }
                filterBangbooList()
            }

            is ZzzResult.Error -> {
                println("get bangboo error: ${result.exception}")
            }
        }
    }

    fun rarityFilterChanged(
        rarities: Set<ZzzRarity>
    ) {
        _uiState.update {
            it.copy(selectedRarity = rarities)
        }
        filterBangbooList()
    }

    fun attributeFilterChanged(
        attributes: Set<AgentAttribute>
    ) {
        _uiState.update {
            it.copy(selectedAttributes = attributes)
        }
        filterBangbooList()
    }


    private fun filterBangbooList(
    ) {
        val filteredAgents = unfilteredBangbooList.value.filter { agent ->
            val matchRarity =
                uiState.value.selectedRarity.isEmpty() || uiState.value.selectedRarity.any { it.level == agent.rarity }
            val matchAttribute =
                uiState.value.selectedAttributes.isEmpty() || uiState.value.selectedAttributes.any { it.name.lowercase() == agent.attribute }

            matchRarity && matchAttribute
        }
        _uiState.update {
            it.copy(
                bangbooList = filteredAgents
            )
        }
    }
}