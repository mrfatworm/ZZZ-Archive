/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.bangboo.components.BangbooListScreenDual
import feature.bangboo.components.BangbooListScreenSingle
import feature.bangboo.model.BangbooListState
import feature.bangboo.presentation.BangbooListAction
import feature.bangboo.presentation.BangbooListViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import utils.UiResult

@Composable
fun BangbooListScreen(
    adaptiveLayoutType: AdaptiveLayoutType,
    onBangbooClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: BangbooListViewModel = koinViewModel()
    val bangbooList = viewModel.bangbooList.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (bangbooList.value) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> ErrorScreen(
            (bangbooList.value as UiResult.Error).message,
            onAction = { viewModel.onAction(BangbooListAction.OnRetry) }
        )

        is UiResult.Success -> {
            BangbooListContent(adaptiveLayoutType, uiState, onAction = { action ->
                when (action) {
                    BangbooListAction.OnBackClick -> onBackClick()
                    is BangbooListAction.OnBangbooClick -> onBangbooClick(action.bangbooId)
                    else -> viewModel.onAction(action)
                }
            })
        }
    }
}

@Composable
private fun BangbooListContent(
    adaptiveLayoutType: AdaptiveLayoutType,
    uiState: State<BangbooListState>,
    onAction: (BangbooListAction) -> Unit,
) {
    if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        BangbooListScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onRarityChipSelectionChanged = {
                onAction(BangbooListAction.OnRarityFilterChanged(it))
            },
            onAttributeChipSelectionChanged = {
                onAction(BangbooListAction.OnAttributeFilterChanged(it))
            },
            onBangbooClick = {
                onAction(BangbooListAction.OnBangbooClick(it))
            },
            onBackClick = {
                onAction(BangbooListAction.OnBackClick)
            }
        )
    } else {
        BangbooListScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onRarityChipSelectionChanged = {
                onAction(BangbooListAction.OnRarityFilterChanged(it))
            },
            onAttributeChipSelectionChanged = {
                onAction(BangbooListAction.OnAttributeFilterChanged(it))
            },
            onBangbooClick = {
                onAction(BangbooListAction.OnBangbooClick(it))
            }
        )
    }
}