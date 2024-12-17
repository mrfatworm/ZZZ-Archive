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
import kotlinx.coroutines.Job
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

    private var agentsListJob: Job? = null
    private var wEnginesListJob: Job? = null
    private var bangbooListJob: Job? = null
    private var drivesListJob: Job? = null

    private var _uiState = MutableStateFlow(WikiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAgentsList()
        observeWEnginesList()
        observeBangbooList()
        observeDrivesList()

    }

    private fun observeAgentsList() {
        agentsListJob?.cancel()
        agentsListJob = viewModelScope.launch {
            agentsListUseCase.invoke().collect { agentsList ->
                _uiState.update {
                    it.copy(agentsList = agentsList)
                }
            }
        }
    }

    private fun observeWEnginesList() {
        wEnginesListJob?.cancel()
        wEnginesListJob = viewModelScope.launch {
            wEnginesListUseCase.invoke().collect { wEnginesList ->
                _uiState.update {
                    it.copy(wEnginesList = wEnginesList)
                }
            }
        }
    }


    private fun observeBangbooList() {
        bangbooListJob?.cancel()
        bangbooListJob = viewModelScope.launch {
            bangbooListUseCase.invoke().collect { bangbooList ->
                _uiState.update {
                    it.copy(bangbooList = bangbooList)
                }
            }
        }
    }

    private fun observeDrivesList() {
        drivesListJob?.cancel()
        drivesListJob = viewModelScope.launch {
            drivesListUseCase.invoke().collect { drivesList ->
                _uiState.update {
                    it.copy(drivesList = drivesList)
                }
            }
        }
    }
}