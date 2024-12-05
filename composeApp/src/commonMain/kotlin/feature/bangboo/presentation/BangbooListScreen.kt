/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.bangboo.components.BangbooListScreenDual
import feature.bangboo.components.BangbooListScreenSingle
import feature.bangboo.model.BangbooListState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType

@Composable
fun BangbooListScreen(
    onBangbooClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: BangbooListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BangbooListContent(uiState, onAction = { action ->
        when (action) {
            BangbooListAction.ClickBack -> onBackClick()
            is BangbooListAction.ClickBangboo -> onBangbooClick(action.bangbooId)
            else -> viewModel.onAction(action)
        }
    })
}

@Composable
private fun BangbooListContent(
    uiState: BangbooListState,
    onAction: (BangbooListAction) -> Unit,
) {
    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        BangbooListScreenSingle(
            uiState = uiState, onAction
        )
    } else {
        BangbooListScreenDual(
            uiState = uiState, onAction
        )
    }
}