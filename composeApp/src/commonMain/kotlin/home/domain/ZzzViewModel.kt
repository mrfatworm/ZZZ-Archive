/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import home.data.ZzzRepository
import home.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.ZzzResult

class ZzzViewModel(private val repository: ZzzRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    fun getActivityTitle() {
        viewModelScope.launch {
            when (val result = repository.getActivities()) {
                is ZzzResult.Success -> {
                    _uiState.value = uiState.value.copy(
                        activityImageUrl = result.data.data.list.first().sTitle
                    )
                }
                is ZzzResult.Error -> {
                    _uiState.value = uiState.value.copy(
                        activityImageUrl = "error"
                    )
                }
            }

        }
    }
}