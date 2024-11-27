/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.wengine.components.WEnginesListScreenDual
import feature.wengine.components.WEnginesListScreenSingle
import feature.wengine.model.WEnginesListState
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun WEnginesListScreen(
    adaptiveLayoutType: AdaptiveLayoutType, onWEngineClick: (Int) -> Unit, onBackClick: () -> Unit
) {
    val viewModel: WEnginesListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WEnginesListContent(adaptiveLayoutType, uiState, onAction = { action ->
        when (action) {
            is WEnginesListAction.OnWEngineClick -> onWEngineClick(action.wEngineId)
            WEnginesListAction.OnBackClick -> onBackClick()
            else -> viewModel.onAction(action)
        }
    })
}

@Composable
private fun WEnginesListContent(
    adaptiveLayoutType: AdaptiveLayoutType,
    uiState: WEnginesListState,
    onAction: (WEnginesListAction) -> Unit
) {
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        WEnginesListScreenSingle(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onWEngineClick = {
                onAction(WEnginesListAction.OnWEngineClick(it))
            },
            onRarityChipSelectionChanged = {
                onAction(WEnginesListAction.OnRarityFilterChanged(it))
            },
            onSpecialtyChipSelectionChanged = {
                onAction(WEnginesListAction.OnSpecialtyFilterChanged(it))
            },
            onBackClick = {
                onAction(WEnginesListAction.OnBackClick)
            })
    } else {
        WEnginesListScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onWEngineClick = {
                onAction(WEnginesListAction.OnWEngineClick(it))
            },
            onRarityChipSelectionChanged = {
                onAction(WEnginesListAction.OnRarityFilterChanged(it))
            },
            onSpecialtyChipSelectionChanged = {
                onAction(WEnginesListAction.OnSpecialtyFilterChanged(it))
            })
    }
}