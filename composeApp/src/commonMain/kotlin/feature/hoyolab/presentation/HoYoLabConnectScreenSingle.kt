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
import feature.hoyolab.model.HoYoLabConnectState
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTopBar
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import ui.utils.containerGap
import ui.utils.contentGap
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.hoyolab_connect
import zzzarchive.composeapp.generated.resources.ic_add

@Composable
fun HoYoLabConnectScreenSingle(
    uiState: HoYoLabConnectState,
    onAction: (HoYoLabConnectAction) -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        ZzzTopBar(
            title = stringResource(Res.string.hoyolab_connect), onBackClick = {
                onAction(HoYoLabConnectAction.ClickBack)
            }
        )

    }) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .contentPaddingInScaffold(contentPadding),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            if (uiState.connectedAccounts.isEmpty()) {
                AddHoYoLabAccountCard(
                    errorMessage = uiState.errorMessage,
                    onSubmit = { serverRegion, lToken, ltUid ->
                        onAction(
                            HoYoLabConnectAction.ConnectToHoYoLabAndAdd(
                                serverRegion, lToken, ltUid
                            )
                        )
                    })
            }

            Column(verticalArrangement = Arrangement.spacedBy(containerGap())) {
                uiState.connectedAccounts.forEach { account ->
                    AccountsListItemCard(uid = account.uid.toString(),
                        regionName = account.regionName,
                        updateAt = account.datetime,
                        isDefault = account.uid == uiState.defaultAccountUid,
                        setAsDefault = {
                            onAction(HoYoLabConnectAction.SetDefaultAccount(account.uid))
                        },
                        delete = {
                            onAction(HoYoLabConnectAction.DeleteAccount(account.uid))
                        })
                }
                if (uiState.connectedAccounts.isNotEmpty()) {
                    ZzzPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.add_account),
                        iconRes = Res.drawable.ic_add
                    ) { onAction(HoYoLabConnectAction.ShowAddAccountDialog(true)) }
                }
            }
        }
    }

}