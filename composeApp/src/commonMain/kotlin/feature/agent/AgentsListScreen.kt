/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import feature.agent.components.AgentsListScreenDual
import feature.agent.components.AgentsListScreenSingle
import feature.agent.domain.AgentsListViewModel
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
    val uiState = viewModel.uiState.collectAsState()
    if (contentType == ContentType.Single) {
        AgentsListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = onAgentClick, onRarityChipSelectionChanged = { newSelection ->
                viewModel.rarityFilterChanged(
                    newSelection
                )
            }, onAttributeChipSelectionChanged = { newSelection ->
                viewModel.attributeFilterChanged(
                    newSelection
                )
            }, onSpecialtyChipSelectionChanged = { newSelection ->
                viewModel.specialtyFilterChanged(
                    newSelection
                )
            }, onFactionChipSelectionChanged = {
                viewModel.factionFilterChanged(it)
            },
            onBackClick = onBackClick
        )
    } else {
        AgentsListScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onAgentClick = onAgentClick,
            onRarityChipSelectionChanged = { newSelection ->
                viewModel.rarityFilterChanged(
                    newSelection
                )
            },
            onAttributeChipSelectionChanged = { newSelection ->
                viewModel.attributeFilterChanged(
                    newSelection
                )
            },
            onSpecialtyChipSelectionChanged = { newSelection ->
                viewModel.specialtyFilterChanged(
                    newSelection
                )
            },
            onFactionClick = {
                viewModel.factionFilterChanged(it)
            }
        )
    }
}