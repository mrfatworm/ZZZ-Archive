/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.agent.model.AgentsListState
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun AgentsListScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentsListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AgentsListContent(uiState = uiState,
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
        })
}

@Composable
fun AgentsListContent(
    uiState: AgentsListState,
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAction: (AgentsListAction) -> Unit,
) {
    if (contentType == ContentType.Single) {
        AgentsListScreenSingle(uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = { id ->
                onAction(AgentsListAction.OnAgentClick(id))
            },
            onRarityChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnRarityFilterChanged(newSelection))
            },
            onAttributeChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnAttributeFilterChanged(newSelection))
            },
            onSpecialtyChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnSpecialtyFilterChanged(newSelection))
            },
            onFactionChipSelectionChanged = {
                onAction(AgentsListAction.OnFactionFilterChanged(it))
            },
            onBackClick = {
                onAction(AgentsListAction.OnBackClick)
            })
    } else {
        AgentsListScreenDual(uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = { id ->
                onAction(AgentsListAction.OnAgentClick(id))
            },
            onRarityChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnRarityFilterChanged(newSelection))
            },
            onAttributeChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnAttributeFilterChanged(newSelection))
            },
            onSpecialtyChipSelectionChanged = { newSelection ->
                onAction(AgentsListAction.OnSpecialtyFilterChanged(newSelection))
            },
            onFactionClick = {
                onAction(AgentsListAction.OnFactionFilterChanged(it))
            })
    }
}