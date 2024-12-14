/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.hoyolab.model.HoYoLabSyncState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.AddHoYoLabAccountDialog
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun HoYoLabSyncScreen(onBackClick: () -> Unit) {
    val viewModel: HoYoLabSyncViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HoYoLabSyncScreenContent(uiState, onAction = { action ->
        when (action) {
            HoYoLabSyncAction.ClickBack -> onBackClick()
            else -> viewModel.onAction(action)
        }
    })
}

@Composable
private fun HoYoLabSyncScreenContent(
    uiState: HoYoLabSyncState, onAction: (HoYoLabSyncAction) -> Unit,
) {
    if (AppTheme.contentType == ContentType.Single) {
        HoYoLabSyncScreenSingle(uiState, onAction)
    } else {
        HoYoLabSyncScreenDual(uiState, onAction)
    }

    when {
        uiState.openAddAccountDialog -> {
            AddHoYoLabAccountDialog(uiState.errorMessage, onSubmit = { region, lToken, ltUid ->
                onAction(
                    HoYoLabSyncAction.ConnectToHoYoLabAndAdd(
                        region, lToken, ltUid
                    )
                )
            }, onDismiss = {
                onAction(HoYoLabSyncAction.ShowAddAccountDialog(false))
            })
        }
    }
}
