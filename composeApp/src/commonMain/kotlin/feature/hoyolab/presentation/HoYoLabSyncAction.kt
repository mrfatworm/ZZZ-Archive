/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

sealed class HoYoLabSyncAction {
    data class ConnectToHoYoLabAndAdd(val region: String, val lToken: String, val ltUid: String) :
        HoYoLabSyncAction()

    data class SyncAccount(val uid: String) : HoYoLabSyncAction()
    data class SetDefaultAccount(val uid: String) : HoYoLabSyncAction()
    data class DeleteAccount(val uid: String) : HoYoLabSyncAction()
    data class ShowAddAccountDialog(val isVisible: Boolean) : HoYoLabSyncAction()
    data object ClickBack : HoYoLabSyncAction()
}