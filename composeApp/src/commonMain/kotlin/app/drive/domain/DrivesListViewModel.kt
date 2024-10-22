/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.drive.data.DriveRepository
import app.drive.model.DrivesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class DrivesListViewModel(
    private val driveRepository: DriveRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(DrivesListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsList()
        }
    }

    private suspend fun fetchAgentsList() {
        when (val result = driveRepository.getDrivesList()) {
            is ZzzResult.Success -> {
                _uiState.update {
                    it.copy(
                        drivesList = result.data.getDrivesNewToOld()
                    )
                }
            }

            is ZzzResult.Error -> {
                println("get bangboo error: ${result.exception}")
            }
        }
    }
}