/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.hoyolab.model.ServersList
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ZzzTextFiled
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.connect
import zzzarchive.composeapp.generated.resources.ic_arrow_back

@Composable
fun HoYoLabConnectScreen(onBackClick: () -> Unit) {
    val viewModel: HoYoLabConnectViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var lToken by remember { mutableStateOf("") }
    var ltUid by remember { mutableStateOf("") }
    Column {
        ZzzIconButton(iconRes = Res.drawable.ic_arrow_back, onClick = onBackClick)
        ZzzTextFiled(
            modifier = Modifier.fillMaxWidth(),
            hint = "ltoken",
            value = lToken,
            onValueChange = {
                lToken = it
            })
        ZzzTextFiled(
            modifier = Modifier.fillMaxWidth(),
            hint = "ltuid",
            value = ltUid,
            onValueChange = {
                ltUid = it
            })
        ZzzPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.connect),
            onClick = {
                viewModel.onAction(
                    HoYoLabConnectAction.ConnectToHoYoLab(
                        ServersList.ASIA.region,
                        lToken,
                        ltUid
                    )
                )
            })
        Text(text = uiState.userName, color = AppTheme.colors.onSurface)
        Text(text = uiState.uid, color = AppTheme.colors.onSurface)
        Text(text = uiState.errorMessage, color = AppTheme.colors.alert)
    }

}