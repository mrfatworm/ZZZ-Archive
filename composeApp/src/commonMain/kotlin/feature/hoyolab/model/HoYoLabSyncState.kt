/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

data class HoYoLabSyncState(
    val syncedAccounts: List<SyncedAccountsListItem> = emptyList(),
    val defaultAccountUid: String = "0",
    val openAddAccountDialog: Boolean = false,
    val syncable: Boolean = true,
    val errorMessage: String = ""
)

data class SyncedAccountsListItem(
    val uid: String,
    val regionName: String,
    val level: String,
    val nickname: String,
    val profileUrl: String,
    val cardUrl: String,
    val datetime: String,
)