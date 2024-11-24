/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.bangboo.components.BangbooDetailScreenDual
import feature.bangboo.components.BangbooDetailScreenSingle
import feature.bangboo.model.BangbooDetailState
import feature.bangboo.presentation.BangbooDetailAction
import feature.bangboo.presentation.BangbooDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import utils.UiResult

@Composable
fun BangbooDetailScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: BangbooDetailViewModel = koinViewModel()
    val bangbooDetail = viewModel.bangbooDetail.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (bangbooDetail.value) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> ErrorScreen(
            (bangbooDetail.value as UiResult.Error).message,
            onAction = { viewModel.onAction(BangbooDetailAction.OnRetry) }
        )

        is UiResult.Success -> {
            BangbooDetailContent(contentType, uiState, adaptiveLayoutType, onBackClick)
        }
    }
}

@Composable
private fun BangbooDetailContent(
    contentType: ContentType,
    uiState: State<BangbooDetailState>,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit
) {
    if (contentType == ContentType.Single) {
        BangbooDetailScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick
        )
    } else {
        BangbooDetailScreenDual(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick
        )
    }
}