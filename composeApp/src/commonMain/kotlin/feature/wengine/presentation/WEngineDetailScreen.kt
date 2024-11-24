/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.wengine.components.WEngineDetailScreenDual
import feature.wengine.components.WEngineDetailScreenSingle
import feature.wengine.model.WEngineDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.components.LoadingScreen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import utils.UiResult

@Composable
fun WEngineDetailScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: WEngineDetailViewModel = koinViewModel()
    val wEngineDetail by viewModel.wEngineDetail.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (wEngineDetail) {
        UiResult.Loading -> LoadingScreen()
        is UiResult.Error -> ErrorScreen(
            (wEngineDetail as UiResult.Error).message,
            onAction = { viewModel.onAction(WEngineDetailAction.OnRetry) }
        )

        is UiResult.Success -> {
            WEngineDetailContent(contentType, uiState, adaptiveLayoutType, onBackClick)
        }
    }
}

@Composable
private fun WEngineDetailContent(
    contentType: ContentType,
    uiState: WEngineDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit
) {
    if (contentType == ContentType.Single) {
        WEngineDetailScreenSingle(
            uiState = uiState, adaptiveLayoutType = adaptiveLayoutType, onBackClick = onBackClick
        )
    } else {
        WEngineDetailScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick
        )
    }
}