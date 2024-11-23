/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import feature.bangboo.components.BangbooListScreenDual
import feature.bangboo.components.BangbooListScreenSingle
import feature.bangboo.domain.BangbooListViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun BangbooListScreen(
    adaptiveLayoutType: AdaptiveLayoutType,
    onBangbooClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: BangbooListViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        BangbooListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onRarityChipSelectionChanged = {
                viewModel.rarityFilterChanged(it)
            },
            onAttributeChipSelectionChanged = {
                viewModel.attributeFilterChanged(it)
            },
            onBangbooClick = onBangbooClick,
            onBackClick = onBackClick
        )
    } else {
        BangbooListScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onRarityChipSelectionChanged = {
                viewModel.rarityFilterChanged(it)
            },
            onAttributeChipSelectionChanged = {
                viewModel.attributeFilterChanged(it)
            },
            onBangbooClick = onBangbooClick
        )
    }
}