/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun MyAgentDetailScreen(onBackClick: () -> Unit) {
    val viewModel: MyAgentDetailViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    MyAgentDetailContent(uiState = uiState.value, onAction = { action ->
        when (action) {
            MyAgentDetailAction.ClickBack -> onBackClick()
        }
    })
}

@Composable
fun MyAgentDetailContent(uiState: MyAgentDetailState, onAction: (MyAgentDetailAction) -> Unit) {
    if (AppTheme.contentType == ContentType.Single) {
        MyAgentDetailScreenSingle(uiState, onAction)
    } else {
        MyAgentDetailScreenDual(uiState, onAction)
    }
}