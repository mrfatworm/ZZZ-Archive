/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.AccountsListItemCard
import feature.hoyolab.components.AddHoYoLabAccountCard
import feature.hoyolab.model.HoYoLabConnectState
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzPrimaryButton
import ui.utils.containerGap
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.ic_add
import zzzarchive.composeapp.generated.resources.ic_arrow_back

@Composable
fun HoYoLabConnectScreenDual(
    uiState: HoYoLabConnectState,
    onAction: (HoYoLabConnectAction) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(containerGap())) {
        Column(
            Modifier.weight(1f).contentPadding(),
            verticalArrangement = Arrangement.spacedBy(containerGap())
        ) {
            ZzzIconButton(iconRes = Res.drawable.ic_arrow_back, onClick = {
                onAction(HoYoLabConnectAction.ClickBack)
            })

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

            LazyColumn(verticalArrangement = Arrangement.spacedBy(containerGap())) {
                items(uiState.connectedAccounts) { account ->
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
                item {
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
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState()).contentPadding()) {

        }
    }

}