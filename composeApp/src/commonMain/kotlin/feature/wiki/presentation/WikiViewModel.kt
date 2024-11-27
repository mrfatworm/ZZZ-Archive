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
import feature.wengine.model.WEnginesListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WikiViewModel(
    private val agentsListUseCase: AgentsListUseCase,
    private val wEnginesListUseCase: WEnginesListUseCase,
    private val bangbooListUseCase: BangbooListUseCase,
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {

    private var _agentsList = MutableStateFlow<List<AgentListItem>>(emptyList())
    val agentsList = _agentsList.asStateFlow()
    private var _wEnginesList = MutableStateFlow<List<WEnginesListItem>>(emptyList())
    val wEnginesList = _wEnginesList.asStateFlow()
    private var _bangbooList = MutableStateFlow<List<BangbooListItem>>(emptyList())
    val bangbooList = _bangbooList.asStateFlow()
    private var _drivesList = MutableStateFlow<List<DrivesListItemEntity>>(emptyList())
    val drivesList = _drivesList.asStateFlow()

    init {
        viewModelScope.launch {
            launch { observeAgentsList() }
            launch { observeWEnginesList() }
            launch { observeBangbooList() }
            launch { observeDrivesList() }
        }
    }

    private suspend fun observeAgentsList() {
        agentsListUseCase.invoke().collect { agentsList ->
            _agentsList.value = agentsList
        }
    }


    private suspend fun observeWEnginesList() {
        wEnginesListUseCase.invoke().collect { wEnginesList ->
            _wEnginesList.value = wEnginesList
        }
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