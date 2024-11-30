/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.agent.model.AgentDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun AgentDetailScreen(
    wEngineClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.error != null) {
        ErrorScreen(uiState.error!!, onAction = {
            viewModel.onAction(AgentDetailAction.OnRetry)
        })
    } else {
        AgentDetailContent(uiState, onAction = { action ->
            when (action) {
                is AgentDetailAction.OnWEngineClick -> wEngineClick(action.wEngineId)
                AgentDetailAction.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        })
    }
}

@Composable
private fun AgentDetailContent(
    uiState: AgentDetailState,
    onAction: (AgentDetailAction) -> Unit,
) {
    if (AppTheme.contentType == ContentType.Single) {
        AgentDetailScreenSingle(uiState = uiState,
            onBackClick = {
                onAction(AgentDetailAction.OnBackClick)
            },
            wEngineClick = { id ->
                onAction(AgentDetailAction.OnWEngineClick(id))
            })
    } else {
        AgentDetailScreenDual(uiState = uiState,
            onBackClick = {
                onAction(AgentDetailAction.OnBackClick)
            },
            wEngineClick = { id ->
                onAction(AgentDetailAction.OnWEngineClick(id))
            })
    }
}