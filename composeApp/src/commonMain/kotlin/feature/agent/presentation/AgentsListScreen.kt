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
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun AgentsListScreen(
    onAgentClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentsListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AgentsListContent(uiState = uiState,
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
    onAction: (AgentsListAction) -> Unit,
) {
    if (AppTheme.contentType == ContentType.Single) {
        AgentsListScreenSingle(uiState = uiState,
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