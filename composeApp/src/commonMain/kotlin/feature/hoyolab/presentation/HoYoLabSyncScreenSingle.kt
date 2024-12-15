/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.AccountsListItemCard
import feature.hoyolab.components.AddHoYoLabAccountCard
import feature.hoyolab.components.HoYoLabAnnouncementCard
import feature.hoyolab.components.HoYoLabSyncGuildCard
import feature.hoyolab.model.HoYoLabSyncState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarRound
import ui.components.TopBarScaffold
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentGap
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.hoyolab_sync
import zzzarchive.composeapp.generated.resources.ic_add

@Composable
fun HoYoLabSyncScreenSingle(
    uiState: HoYoLabSyncState,
    onAction: (HoYoLabSyncAction) -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            TopBarScaffold(
                title = stringResource(Res.string.hoyolab_sync), onBackClick = {
                    onAction(HoYoLabSyncAction.ClickBack)
                }
            )
        }
    }) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .contentPaddingInScaffold(contentPadding),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Medium) {
                TopBarRound(title = stringResource(Res.string.hoyolab_sync), onBackClick = {
                    onAction(HoYoLabSyncAction.ClickBack)
                })
            }
            HoYoLabAnnouncementCard()
            if (uiState.syncedAccounts.isEmpty()) {
                AddHoYoLabAccountCard(
                    errorMessage = uiState.errorMessage,
                    onSubmit = { serverRegion, lToken, ltUid ->
                        onAction(
                            HoYoLabSyncAction.ConnectToHoYoLabAndAdd(
                                serverRegion, lToken, ltUid
                            )
                        )
                    })
            }

            Column(verticalArrangement = Arrangement.spacedBy(contentGap())) {
                uiState.syncedAccounts.forEach { account ->
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
                if (uiState.syncedAccounts.isNotEmpty()) {
                    ZzzPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.add_account),
                        iconRes = Res.drawable.ic_add
                    ) { onAction(HoYoLabSyncAction.ShowAddAccountDialog(true)) }
                }
            }
            HoYoLabSyncGuildCard(navigateToFeedback = {
                onAction(HoYoLabSyncAction.NavigateToFeedback)
            })
        }
    }

}