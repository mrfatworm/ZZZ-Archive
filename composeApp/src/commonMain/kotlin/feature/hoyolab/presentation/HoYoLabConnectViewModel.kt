/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabConnectUseCase
import feature.hoyolab.model.HoYoLabConnectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HoYoLabConnectViewModel(
    private val hoYoLabConnectUseCase: HoYoLabConnectUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(HoYoLabConnectState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: HoYoLabConnectAction) {
        when (action) {
            is HoYoLabConnectAction.ConnectToHoYoLab -> {
                connectToHoYoLab(action.region, action.lToken, action.ltUid)
            }

            is HoYoLabConnectAction.ChangeLToken -> {

            }

            is HoYoLabConnectAction.ChangeLtUid -> {

            }

            else -> {}
        }
    }

    private fun connectToHoYoLab(server: String, lToken: String, ltUid: String) {
        viewModelScope.launch {
            val result = hoYoLabConnectUseCase.requestUserGameRoles(server, lToken, ltUid)
            result.fold(onSuccess = {
                if (it.isEmpty()) {
                    _uiState.update { state ->
                        state.copy(
                            errorMessage = "No account found"
                        )
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(
                            userName = it.first().nickname, uid = it.first().uid
                        )
                    }
                }
            }, onFailure = {
                _uiState.update { state ->
                    state.copy(
                        errorMessage = "Request Error"
                    )
                }
            })
        }
    }

}