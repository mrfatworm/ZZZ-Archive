/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.drive.domain.DrivesListUseCase
import feature.wengine.domain.WEnginesListUseCase
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
            _uiState.update {
                it.copy(agentsList = agentsList)
            }
        }
    }

    private suspend fun observeWEnginesList() {
        wEnginesListUseCase.invoke().collect { wEnginesList ->
            _uiState.update {
                it.copy(wEnginesList = wEnginesList)
            }
        }
    }

    private suspend fun observeBangbooList() {
        bangbooListUseCase.invoke().collect { bangbooList ->
            _uiState.update {
                it.copy(bangbooList = bangbooList)
            }
        }
    }


    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _uiState.update {
                it.copy(drivesList = drivesList)
            }
        }
    }
}