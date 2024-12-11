/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

sealed class HoYoLabConnectAction {
    data class ConnectToHoYoLab(val region: String, val lToken: String, val ltUid: String) :
        HoYoLabConnectAction()

    data class ChangeLToken(val lToken: String) : HoYoLabConnectAction()
    data class ChangeLtUid(val ltUid: String) : HoYoLabConnectAction()
    data object ClickBack : HoYoLabConnectAction()
}