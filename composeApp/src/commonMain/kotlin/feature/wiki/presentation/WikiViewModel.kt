/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.AgentListItem
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.BangbooListItem
import feature.drive.data.database.DrivesListItemEntity
import feature.drive.domain.DrivesListUseCase
import feature.wengine.domain.WEnginesListUseCase
import feature.wiki.model.WikiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WikiViewModel(
    private val agentsListUseCase: AgentsListUseCase,
    private val wEnginesListUseCase: WEnginesListUseCase,
    private val bangbooListUseCase: BangbooListUseCase,
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(WikiState())
    val uiState = _uiState.asStateFlow()
    private var _agentsList = MutableStateFlow<List<AgentListItem>>(emptyList())
    val agentsList = _agentsList.asStateFlow()
    private var _bangbooList = MutableStateFlow<List<BangbooListItem>>(emptyList())
    val bangbooList = _bangbooList.asStateFlow()
    private var _drivesList = MutableStateFlow<List<DrivesListItemEntity>>(emptyList())
    val drivesList = _drivesList.asStateFlow()

    init {
        viewModelScope.launch {
            launch { observeAgentsList() }
            launch { fetchWEnginesList() }
            launch { observeBangbooList() }
            launch { observeDrivesList() }
        }
    }

    private suspend fun observeAgentsList() {
        agentsListUseCase.invoke().collect { agentsList ->
            _agentsList.value = agentsList
        }
    }

    private suspend fun fetchWEnginesList() {
        val result = wEnginesListUseCase.invoke()
        result.fold(onSuccess = { wEnginesList ->
            _uiState.update {
                it.copy(wEnginesList = wEnginesList)
            }
        }, onFailure = {
            println("get wEngines error: ${it.message}")
        })
    }

    private suspend fun observeBangbooList() {
        bangbooListUseCase.invoke().collect { bangbooList ->
            _bangbooList.value = bangbooList
        }
    }


    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _drivesList.value = drivesList
        }
    }

}