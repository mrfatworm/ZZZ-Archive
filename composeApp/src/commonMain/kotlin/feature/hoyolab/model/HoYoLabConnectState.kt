/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

data class HoYoLabConnectState(
    val connectedAccounts: List<ConnectedAccountsListItem> = emptyList(),
    val errorMessage: String = ""
)

data class ConnectedAccountsListItem(
    val uid: String,
    val regionName: String,
)
