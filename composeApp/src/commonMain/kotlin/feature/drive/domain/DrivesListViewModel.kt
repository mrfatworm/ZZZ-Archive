/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.drive.data.DriveRepository
import feature.drive.model.DrivesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class DrivesListViewModel(
    private val driveRepository: DriveRepository
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
                println("get drive error: ${result.exception}")
            }
        }
    }

    fun onDriveClick(driveId: Int) {
        _uiState.update { currentState ->
            currentState.copy(selectedDrive = currentState.drivesList.find { it.id == driveId })
        }
    }

    fun onDetailDismiss() {
        _uiState.update {
            it.copy(
                selectedDrive = null
            )
        }
    }
}