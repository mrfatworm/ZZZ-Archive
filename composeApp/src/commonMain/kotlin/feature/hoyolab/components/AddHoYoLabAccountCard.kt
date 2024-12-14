/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.hoyolab.model.ServersList
import feature.setting.components.SettingItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ZzzTextFiled
import ui.components.buttons.ZzzPrimaryButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.clear
import zzzarchive.composeapp.generated.resources.game_server
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.ic_warning
import zzzarchive.composeapp.generated.resources.please_select
import zzzarchive.composeapp.generated.resources.sync
import zzzarchive.composeapp.generated.resources.unknown_error

@Composable
fun AddHoYoLabAccountCard(errorMessage: String, onSubmit: (String, String, String) -> Unit) {
    var lToken by remember { mutableStateOf("") }
    var ltUid by remember { mutableStateOf("") }
    var selectedServerRegion by remember { mutableStateOf(ServersList.None) }
    var isLTokenError by remember { (mutableStateOf(false)) }
    var isLtUidError by remember { (mutableStateOf(false)) }
    var isServerRegionError by remember { (mutableStateOf(false)) }
    ContentCard(hasDefaultPadding = false) {
        Spacer(Modifier.size(AppTheme.spacing.s200))
        ServerTypeItem(ServersList.entries.dropLast(1),
            isError = isServerRegionError,
            onServerSelected = {
                selectedServerRegion = it
                isServerRegionError = it == ServersList.None
            })
        Column(
            modifier = Modifier.padding(AppTheme.spacing.s400),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
        ) {
            SecretTextField(hint = "ltoken", value = lToken, onValueChange = {
                lToken = it
                isLTokenError = it.isEmpty()
            })
            SecretTextField(hint = "ltuid", value = ltUid, onValueChange = {
                ltUid = it
                isLtUidError = it.isEmpty()
            })
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = errorMessage, color = AppTheme.colors.alert)

                ZzzPrimaryButton(text = stringResource(Res.string.sync), onClick = {
                    if (lToken.isEmpty() || ltUid.isEmpty() || selectedServerRegion == ServersList.None) {
                        isLTokenError = lToken.isEmpty()
                        isLtUidError = ltUid.isEmpty()
                        isServerRegionError = selectedServerRegion == ServersList.None
                    } else {
                        onSubmit(
                            selectedServerRegion.region, lToken, ltUid
                        )
                    }
                })
            }
        }
        Spacer(Modifier.size(AppTheme.spacing.s200))
    }
}

@Composable
private fun SecretTextField(
    hint: String, isError: Boolean = false, value: String, onValueChange: (String) -> Unit
) {
    ZzzTextFiled(modifier = Modifier.fillMaxWidth(),
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        maxLines = 4,
        trailingIcon = {
            IconButton(onClick = {
                onValueChange("")
            }) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_close),
                    contentDescription = stringResource(Res.string.clear),
                    tint = AppTheme.colors.onSurface
                )
            }
        })
}

@Composable
private fun ServerTypeItem(
    gameServerType: List<ServersList>, isError: Boolean, onServerSelected: (ServersList) -> Unit
) {
    val pleaseSelectText = stringResource(Res.string.please_select)
    var selectedServerType by remember { mutableStateOf(ServersList.None) }
    var showServerTypesDropdown by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.game_server), content = {
        Column(horizontalAlignment = Alignment.End) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                if (isError) {
                    Icon(
                        modifier = Modifier.size(AppTheme.size.iconSize),
                        imageVector = vectorResource(Res.drawable.ic_warning),
                        contentDescription = stringResource(Res.string.unknown_error),
                        tint = AppTheme.colors.secondary
                    )
                }
                Text(
                    text = if (selectedServerType == ServersList.None) pleaseSelectText else selectedServerType.localName,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.smallIconSize),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showServerTypesDropdown,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showServerTypesDropdown = false }) {
                gameServerType.forEach { gameServer ->
                    DropdownMenuItem(text = {
                        Text(
                            text = gameServer.localName,
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        selectedServerType = gameServer
                        onServerSelected(gameServer)
                        showServerTypesDropdown = false
                    })
                }
            }
        }
    }, onClick = {
        showServerTypesDropdown = true
    })
}
