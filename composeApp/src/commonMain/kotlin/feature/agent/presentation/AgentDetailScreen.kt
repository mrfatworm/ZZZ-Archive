/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.agent.components.AgentDetailScreenDual
import feature.agent.components.AgentDetailScreenSingle
import feature.agent.model.AgentDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import utils.UiResult

@Composable
fun AgentDetailScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    wEngineClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentDetailViewModel = koinViewModel()
    val agentDetailState by viewModel.agentDetailState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (agentDetailState) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> ErrorScreen(
            (agentDetailState as UiResult.Error).message,
            onAction = {
                viewModel.onAction(AgentDetailAction.OnRetry)
            })

        is UiResult.Success -> {
            AgentDetailContent(contentType, uiState, adaptiveLayoutType, onAction = { action ->
                when (action) {
                    is AgentDetailAction.OnWEngineClick -> wEngineClick(action.wEngineId)
                    AgentDetailAction.OnBackClick -> onBackClick()
                    else -> viewModel.onAction(action)
                }
            })
        }
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