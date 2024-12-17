/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.DrivesListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrivesListViewModel(
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {

    private var drivesListJob: Job? = null

    private var _uiState = MutableStateFlow(DrivesListState())
    val uiState = _uiState.asStateFlow()

    init {
        observeDrivesList()
    }

    fun onAction(action: DrivesListAction) {
        when (action) {
            DrivesListAction.DismissDriveDetail -> onDetailDismiss()
            is DrivesListAction.ClickDriveDetail -> onDriveClick(action.id)
            else -> {}
        }
    }

    private fun observeDrivesList() {
        drivesListJob?.cancel()
        drivesListJob = viewModelScope.launch {
            drivesListUseCase.invoke().collect { drivesList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        drivesList = drivesList
                    )
                }
            }
        }
    }

    private fun onDriveClick(driveId: Int) {
        _uiState.update { currentState ->
            currentState.copy(selectedDrive = currentState.drivesList.find { it.id == driveId })
        }
    }

    private fun onDetailDismiss() {
        _uiState.update {
            it.copy(
                selectedDrive = null
            )
        }
    }
}