/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.wengine.compose.WEnginesListScreenDual
import app.wengine.compose.WEnginesListScreenSingle
import app.wengine.domain.WEnginesListViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun WEnginesListScreen(
    adaptiveLayoutType: AdaptiveLayoutType, onWEngineClick: (Int) -> Unit, onBackClick: () -> Unit
) {
    val viewModel: WEnginesListViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        WEnginesListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onWEngineClick = onWEngineClick,
            onRarityChipSelectionChanged = {
                viewModel.rarityFilterChanged(it)
            },
            onSpecialtyChipSelectionChanged = {
                viewModel.specialtyFilterChanged(it)
            },
            onBackClick = onBackClick
        )
    } else {
        WEnginesListScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onWEngineClick = onWEngineClick,
            onRarityChipSelectionChanged = {
                viewModel.rarityFilterChanged(it)
            },
            onSpecialtyChipSelectionChanged = {
                viewModel.specialtyFilterChanged(it)
            }
        )
    }
}