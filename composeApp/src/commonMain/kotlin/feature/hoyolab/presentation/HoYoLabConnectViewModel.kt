/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.model.HoYoLabConnectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HoYoLabConnectViewModel(
    private val hoYoLabManageUseCase: HoYoLabManageUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(HoYoLabConnectState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeAccountList()
        }
    }

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
            val result = hoYoLabManageUseCase.requestUserGameRoles(server, lToken, ltUid)
            result.fold(onSuccess = { accountInfo ->
                if (accountInfo.isEmpty()) {
                    _uiState.update { state ->
                        state.copy(
                            errorMessage = "No account found"
                        )
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(
                            userName = accountInfo.first().nickname, uid = accountInfo.first().uid
                        )
                    }
                    hoYoLabManageUseCase.encryptAndSaveToDatabase(
                        region = server,
                        regionName = accountInfo.first().regionName,
                        lToken = lToken,
                        ltUid = ltUid,
                        uid = accountInfo.first().uid
                    )
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

    private suspend fun observeAccountList() {
        hoYoLabManageUseCase.getAccountFromDB().collect { accountList ->
            if (accountList.isEmpty()) {
                _uiState.update { state ->
                    state.copy(
                        errorMessage = "No account found"
                    )
                }
                return@collect
            }

            println("lToken: ${accountList.first().lToken}")
            println("ltUid: ${accountList.first().ltUid}")

            _uiState.update { state ->
                state.copy(
                    userName = accountList.first().regionName,
                    uid = accountList.first().uid.toString()
                )
            }
        }
    }
}