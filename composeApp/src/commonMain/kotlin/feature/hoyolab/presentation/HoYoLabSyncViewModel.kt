/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.hoyolab.domain.HoYoLabManageUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import feature.hoyolab.model.HoYoLabSyncState
import feature.hoyolab.model.SyncedAccountsListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HoYoLabSyncViewModel(
    private val hoYoLabManageUseCase: HoYoLabManageUseCase,
    private val hoYoLabPreferenceUseCase: HoYoLabPreferenceUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(HoYoLabSyncState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch { observeAccountList() }
            launch { observeDefaultAccountUid() }
        }
    }

    fun onAction(action: HoYoLabSyncAction) {
        when (action) {
            is HoYoLabSyncAction.ConnectToHoYoLabAndAdd -> {
                viewModelScope.launch {
                    connectToHoYoLabAndSaveToDB(action.region, action.lToken, action.ltUid)
                }
            }

            is HoYoLabSyncAction.SyncAccount -> {
                reSyncAccountAndCoolDown(action)
            }

            is HoYoLabSyncAction.DeleteAccount -> {
                viewModelScope.launch {
                    hoYoLabManageUseCase.deleteAccountFromDB(action.uid.toInt())
                }
            }

            is HoYoLabSyncAction.ShowAddAccountDialog -> {
                _uiState.update { state ->
                    state.copy(openAddAccountDialog = action.isVisible)
                }
            }

            is HoYoLabSyncAction.SetDefaultAccount -> {
                viewModelScope.launch {
                    setDefaultHoYoLabAccountUid(action.uid.toInt())
                }
            }

            else -> {}
        }
    }
    private suspend fun observeDefaultAccountUid() {
        hoYoLabPreferenceUseCase.getDefaultHoYoLabAccountUid().collect { uid ->
            _uiState.update { state ->
                state.copy(defaultAccountUid = uid.toString())
            }
        }
    }

    private suspend fun observeAccountList() {
        hoYoLabManageUseCase.getAllAccountsFromDB().collect { accountList ->
            _uiState.update { state ->
                state.copy(syncedAccounts = accountList.map {
                    SyncedAccountsListItem(
                        uid = it.uid.toString(),
                        regionName = it.regionName,
                        level = it.level.toString(),
                        nickname = it.nickName,
                        profileUrl = it.profileUrl,
                        cardUrl = it.cardUrl,
                        datetime = hoYoLabManageUseCase.convertToLocalDatetime(it.updatedAt)
                    )
                })
            }
        }
    }

    private suspend fun connectToHoYoLabAndSaveToDB(server: String, lToken: String, ltUid: String) {
        val result = hoYoLabManageUseCase.requestUserInfoAndSave(server, lToken, ltUid)
        result.fold(onSuccess = {
            _uiState.update { state ->
                state.copy(
                    openAddAccountDialog = false, errorMessage = ""
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

    private fun reSyncAccountAndCoolDown(action: HoYoLabSyncAction.SyncAccount) {
        if (!uiState.value.syncable) return
        _uiState.update { it.copy(syncable = false) }
        viewModelScope.launch {
            hoYoLabManageUseCase.reSyncAccount(action.uid.toInt())
            delay(8000)
            _uiState.update { it.copy(syncable = true) }
        }
    }

    private suspend fun setDefaultHoYoLabAccountUid(uid: Int) {
        hoYoLabPreferenceUseCase.setDefaultHoYoLabAccountUid(uid)
    }
}