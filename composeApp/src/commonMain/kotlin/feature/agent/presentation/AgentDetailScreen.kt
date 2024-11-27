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
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun AgentDetailScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
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
        AgentDetailContent(contentType, uiState, adaptiveLayoutType, onAction = { action ->
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
    contentType: ContentType,
    uiState: AgentDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAction: (AgentDetailAction) -> Unit,
) {
    if (contentType == ContentType.Single) {
        AgentDetailScreenSingle(uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = {
                onAction(AgentDetailAction.OnBackClick)
            },
            wEngineClick = { id ->
                onAction(AgentDetailAction.OnWEngineClick(id))
            })
    } else {
        AgentDetailScreenDual(uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = {
                onAction(AgentDetailAction.OnBackClick)
            },
            wEngineClick = { id ->
                onAction(AgentDetailAction.OnWEngineClick(id))
            })
    }
}