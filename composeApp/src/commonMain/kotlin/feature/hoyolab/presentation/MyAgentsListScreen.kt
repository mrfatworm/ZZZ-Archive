/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.hoyolab.model.MyAgentsListState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType

@Composable
fun MyAgentsListScreen(
    onAgentClick: (Int) -> Unit = {},
    onBackClick: () -> Unit
) {
    val viewModel: MyAgentsListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MyAgentsListContent(
        uiState = uiState,
        onAction = { action ->
            when (action) {
                is MyAgentsListAction.ClickAgent -> {
                    onAgentClick(action.agentId)
                }

                MyAgentsListAction.ClickBack -> onBackClick()
            }
        })
}

@Composable
fun MyAgentsListContent(
    uiState: MyAgentsListState,
    onAction: (MyAgentsListAction) -> Unit,
) {
    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        MyAgentsListScreenCompact(uiState = uiState, onAction)
    } else {
        MyAgentsListScreenMedium(uiState = uiState, onAction)
    }
}