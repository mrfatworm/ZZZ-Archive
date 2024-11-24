/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.drive.data.DriveRepository
import feature.wengine.domain.WEnginesListUseCase
import feature.wiki.model.WikiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class WikiViewModel(
    private val agentsListUseCase: AgentsListUseCase,
    private val wEnginesListUseCase: WEnginesListUseCase,
    private val bangbooListUseCase: BangbooListUseCase,
    private val driveRepository: DriveRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(WikiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch { fetchAgentsList() }
            launch { fetchWEnginesList() }
            launch { fetchBangbooList() }
            launch { fetchDrivesList() }
        }
    }

    private suspend fun fetchAgentsList() {
        val result = agentsListUseCase.invoke()
        result.fold(onSuccess = { agentsList ->
            _uiState.update {
                it.copy(agentsList = agentsList)
            }
        }, onFailure = {
            println("get agents error: ${it.message}")
        })
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

    private suspend fun fetchBangbooList() {
        val result = bangbooListUseCase.invoke()
        result.fold(onSuccess = { bangbooList ->
            _uiState.update {
                it.copy(bangbooList = bangbooList)
            }
        }, onFailure = {
            println("get bangboos error: ${it.message}")
        })
    }

    private suspend fun fetchDrivesList() {
        when (val result = driveRepository.getDrivesList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(drivesList = result.data.getDrivesNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get drives error: ${result.exception}")
            }
        }
    }

}