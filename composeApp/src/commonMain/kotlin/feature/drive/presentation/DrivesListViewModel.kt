/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.DriveListItem
import feature.drive.model.DrivesListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.UiResult

class DrivesListViewModel(
    private val drivesListUseCase: DrivesListUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(DrivesListState())
    val uiState = _uiState.asStateFlow()
    private var _drivesList = MutableStateFlow<UiResult<List<DriveListItem>>>(UiResult.Loading)
    val drivesList = _drivesList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAgentsList()
        }
    }

    private suspend fun fetchAgentsList() {
        _drivesList.value = UiResult.Loading
        val result = drivesListUseCase.invoke()
        _drivesList.value = result.fold(onSuccess = {
            _uiState.update { currentState ->
                currentState.copy(drivesList = it)
                }
            UiResult.Success(it)
        }, onFailure = { UiResult.Error(it.message ?: "Unknown Error") })
    }

    fun onRetryClick() {
        viewModelScope.launch {
            fetchAgentsList()
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