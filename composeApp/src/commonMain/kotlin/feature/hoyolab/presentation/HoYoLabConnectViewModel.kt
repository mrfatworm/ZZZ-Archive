/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrfatworm.zzzarchive.ZzzConfig
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.model.ConnectedAccountsListItem
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

        println("Lance Key = ${ZzzConfig.AES_KEY}")
    }

    fun onAction(action: HoYoLabConnectAction) {
        when (action) {
            is HoYoLabConnectAction.ConnectToHoYoLabAndAdd -> {
                viewModelScope.launch {
                    connectToHoYoLab(action.region, action.lToken, action.ltUid)
                }
            }

            is HoYoLabConnectAction.DeleteAccount -> {
                viewModelScope.launch {
                    hoYoLabManageUseCase.deleteAccountFromDB(action.uid)
                }
            }

            else -> {}
        }
    }

    private suspend fun observeAccountList() {
        hoYoLabManageUseCase.getAllAccountsFromDB().collect { accountList ->
            println("lToken: ${accountList.first().lToken}")
            println("ltUid: ${accountList.first().ltUid}")

            _uiState.update { state ->
                state.copy(connectedAccounts = accountList.map {
                    ConnectedAccountsListItem(
                        uid = it.uid.toString(), regionName = it.regionName
                    )
                })
            }
        }
    }

    private suspend fun connectToHoYoLab(server: String, lToken: String, ltUid: String) {
        val result = hoYoLabManageUseCase.requestUserGameRolesAndSave(server, lToken, ltUid)
        result.fold(onSuccess = {

        }, onFailure = {
            _uiState.update { state ->
                state.copy(
                    errorMessage = "Request Error"
                )
            }
        })
    }
}