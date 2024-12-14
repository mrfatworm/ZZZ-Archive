/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.hoyolab.model.HoYoLabConnectState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.AddHoYoLabAccountDialog
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun HoYoLabConnectScreen(onBackClick: () -> Unit) {
    val viewModel: HoYoLabConnectViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HoYoLabConnectScreenContent(uiState, onAction = { action ->
        when (action) {
            HoYoLabConnectAction.ClickBack -> onBackClick()
            else -> viewModel.onAction(action)
        }
    })
}

@Composable
private fun HoYoLabConnectScreenContent(
    uiState: HoYoLabConnectState, onAction: (HoYoLabConnectAction) -> Unit,
) {
    if (AppTheme.contentType == ContentType.Single) {
        HoYoLabConnectScreenSingle(uiState, onAction)
    } else {
        HoYoLabConnectScreenDual(uiState, onAction)
    }

    when {
        uiState.openAddAccountDialog -> {
            AddHoYoLabAccountDialog(uiState.errorMessage, onSubmit = { region, lToken, ltUid ->
                onAction(
                    HoYoLabConnectAction.ConnectToHoYoLabAndAdd(
                        region, lToken, ltUid
                    )
                )
            }, onDismiss = {
                onAction(HoYoLabConnectAction.ShowAddAccountDialog(false))
            })
        }
    }
}
