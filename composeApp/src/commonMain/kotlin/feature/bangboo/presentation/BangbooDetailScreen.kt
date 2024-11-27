/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.bangboo.components.BangbooDetailScreenDual
import feature.bangboo.components.BangbooDetailScreenSingle
import feature.bangboo.model.BangbooDetailState
import org.koin.compose.viewmodel.koinViewModel
import ui.components.ErrorScreen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun BangbooDetailScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: BangbooDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.error != null) {
        ErrorScreen(message = uiState.error!!,
            onAction = { viewModel.onAction(BangbooDetailAction.OnRetry) })
    } else {
        BangbooDetailContent(contentType, uiState, adaptiveLayoutType, onBackClick)
    }
}

@Composable
private fun BangbooDetailContent(
    contentType: ContentType, uiState: BangbooDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit
) {
    if (contentType == ContentType.Single) {
        BangbooDetailScreenSingle(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick
        )
    } else {
        BangbooDetailScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick
        )
    }
}