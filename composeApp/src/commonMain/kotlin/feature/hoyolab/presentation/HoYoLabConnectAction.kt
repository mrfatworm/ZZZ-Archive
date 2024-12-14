/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

sealed class HoYoLabConnectAction {
    data class ConnectToHoYoLabAndAdd(val region: String, val lToken: String, val ltUid: String) :
        HoYoLabConnectAction()
    data class SetDefaultAccount(val uid: Int) : HoYoLabConnectAction()
    data class DeleteAccount(val uid: Int) : HoYoLabConnectAction()
    data class ShowAddAccountDialog(val isVisible: Boolean) : HoYoLabConnectAction()
    data object ClickBack : HoYoLabConnectAction()
}