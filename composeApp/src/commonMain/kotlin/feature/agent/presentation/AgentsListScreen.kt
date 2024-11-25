/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.agent.components.AgentsListScreenDual
import feature.agent.components.AgentsListScreenSingle
import feature.agent.model.AgentsListState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import utils.UiResult

@Composable
fun AgentsListScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentsListViewModel = koinViewModel()
    val agentsList = viewModel.agentsList.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (agentsList.value) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> {
            ErrorScreen(
                message = (agentsList.value as UiResult.Error).message,
                onAction = {
                    viewModel.onAction(AgentsListAction.OnRetry)
                }
            )
        }

        is UiResult.Success -> {
            AgentsListContent(
                uiState = uiState.value,
                contentType = contentType,
                adaptiveLayoutType = adaptiveLayoutType,
                onAction = { action ->
                    when (action) {
                        is AgentsListAction.OnAgentClick -> {
                            onAgentClick(action.agentId)
                        }

                        AgentsListAction.OnBackClick -> onBackClick()
                        else -> viewModel.onAction(action)
                    }
                }
            )
        }
    }
}

@Composable
fun AgentsListContent(
    uiState: AgentsListState,
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAction: (AgentsListAction) -> Unit,
) {
    if (contentType == ContentType.Single) {
        AgentsListScreenSingle(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = { id ->
                onAction(AgentsListAction.OnAgentClick(id))
            }, onRarityChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnRarityFilterChanged(newSelection))
            }, onAttributeChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnAttributeFilterChanged(newSelection))
            }, onSpecialtyChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnSpecialtyFilterChanged(newSelection))
            }, onFactionChipSelectionChanged = {
                onAction(AgentsListAction.OnFactionFilterChanged(it))
            },
            onBackClick = {
                onAction(AgentsListAction.OnBackClick)
            }
        )
    } else {
        AgentsListScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = { id ->
                onAction(AgentsListAction.OnAgentClick(id))
            }, onRarityChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnRarityFilterChanged(newSelection))
            }, onAttributeChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnAttributeFilterChanged(newSelection))
            }, onSpecialtyChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnSpecialtyFilterChanged(newSelection))
            }, onFactionClick = {
                onAction(AgentsListAction.OnFactionFilterChanged(it))
            }
        )
    }
}