/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.DrivesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrivesListViewModel(
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(DrivesListState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeDrivesList()
        }
    }

    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _uiState.update { currentState ->
                currentState.copy(
                    drivesList = drivesList
                )
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