/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.domain.HoYoLabSettingUseCase
import feature.hoyolab.model.ConnectedAccountsListItem
import feature.hoyolab.model.HoYoLabConnectState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HoYoLabConnectViewModel(
    private val hoYoLabManageUseCase: HoYoLabManageUseCase,
    private val hoYoLabSettingUseCase: HoYoLabSettingUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(HoYoLabConnectState())
    val uiState = _uiState.asStateFlow()

    init {
        getDefaultHoYoLabAccountUid()
        viewModelScope.launch {
            observeAccountList()
        }
    }

    fun onAction(action: HoYoLabConnectAction) {
        when (action) {
            is HoYoLabConnectAction.ConnectToHoYoLabAndAdd -> {
                viewModelScope.launch {
                    connectToHoYoLab(action.region, action.lToken, action.ltUid)
                }
            }

            is HoYoLabConnectAction.DeleteAccount -> {
                val deleteAccountDeferred = viewModelScope.async {
                    hoYoLabManageUseCase.deleteAccountFromDB(action.uid)
                }
                viewModelScope.launch {
                    deleteAccountDeferred.await()
                    getDefaultHoYoLabAccountUid()
                }
            }

            is HoYoLabConnectAction.ShowAddAccountDialog -> {
                _uiState.update { state ->
                    state.copy(openAddAccountDialog = action.isVisible)
                }
            }

            is HoYoLabConnectAction.SetDefaultAccount -> {
                setDefaultHoYoLabAccountUid(action.uid)
            }

            else -> {}
        }
    }

    private suspend fun observeAccountList() {
        hoYoLabManageUseCase.getAllAccountsFromDB().collect { accountList ->
//            println("lToken: ${accountList.firstOrNull()?.lToken}")
//            println("ltUid: ${accountList.firstOrNull()?.ltUid}")

            _uiState.update { state ->
                state.copy(connectedAccounts = accountList.map {
                    ConnectedAccountsListItem(
                        uid = it.uid,
                        regionName = it.regionName,
                        datetime = hoYoLabManageUseCase.convertToLocalDatetime(it.updatedAt)
                    )
                })
            }
        }
    }

    private suspend fun connectToHoYoLab(server: String, lToken: String, ltUid: String) {
        val result = hoYoLabManageUseCase.requestUserGameRolesAndSave(server, lToken, ltUid)
        result.fold(onSuccess = {
            _uiState.update { state ->
                state.copy(
                    openAddAccountDialog = false,
                    errorMessage = ""
                )
            }
        }, onFailure = {
            _uiState.update { state ->
                state.copy(
                    errorMessage = it.message ?: "Unknown error"
                )
            }
        })
    }

    private fun getDefaultHoYoLabAccountUid() {
        _uiState.update { state ->
            state.copy(
                defaultAccountUid = hoYoLabSettingUseCase.getDefaultHoYoLabAccountUid()
            )
        }
    }

    private fun setDefaultHoYoLabAccountUid(uid: Int) {
        hoYoLabSettingUseCase.setDefaultHoYoLabAccountUid(uid)
        getDefaultHoYoLabAccountUid()
    }
}