/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.AccountsListItemCard
import feature.hoyolab.components.AddHoYoLabAccountCard
import feature.hoyolab.components.HoYoLabAnnouncementCard
import feature.hoyolab.components.HoYoLabSyncGuildCard
import feature.hoyolab.model.HoYoLabSyncState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarRound
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import ui.utils.containerGap
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.hoyolab_sync
import zzzarchive.composeapp.generated.resources.ic_add

@Composable
fun HoYoLabSyncScreenDual(
    uiState: HoYoLabSyncState, onAction: (HoYoLabSyncAction) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.surface)
            .padding(horizontalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(containerGap())
    ) {
        Column(
            Modifier.weight(1f).padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(containerGap())
        ) {
            TopBarRound(title = stringResource(Res.string.hoyolab_sync), onBackClick = {
                onAction(HoYoLabSyncAction.ClickBack)
            })

            HoYoLabAnnouncementCard()

            if (uiState.syncedAccounts.isEmpty()) {
                AddHoYoLabAccountCard(
                    errorMessage = uiState.errorMessage, onSubmit = { serverRegion, lToken, ltUid ->
                        onAction(
                            HoYoLabSyncAction.ConnectToHoYoLabAndAdd(
                                serverRegion, lToken, ltUid
                            )
                        )
                    })
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(contentGap())) {
                items(uiState.syncedAccounts) { account ->
                    AccountsListItemCard(
                        uiState = account,
                        isDefault = account.uid == uiState.defaultAccountUid,
                        syncable = uiState.syncable,
                        sync = {
                            onAction(HoYoLabSyncAction.SyncAccount(account.uid))
                        },
                        setAsDefault = {
                            onAction(HoYoLabSyncAction.SetDefaultAccount(account.uid))
                        },
                        delete = {
                            onAction(HoYoLabSyncAction.DeleteAccount(account.uid))
                        })
                }
                item {
                    if (uiState.syncedAccounts.isNotEmpty()) {
                        ZzzPrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.add_account),
                            iconRes = Res.drawable.ic_add
                        ) { onAction(HoYoLabSyncAction.ShowAddAccountDialog(true)) }
                    }
                }
            }

        }
        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(verticalSafePadding())
        ) {
            HoYoLabSyncGuildCard(navigateToFeedback = {
                onAction(HoYoLabSyncAction.NavigateToFeedback)
            })
        }
    }

}