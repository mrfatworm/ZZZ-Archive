/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import feature.wengine.compose.WEngineDetailScreenDual
import feature.wengine.compose.WEngineDetailScreenSingle
import feature.wengine.domain.WEngineDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun WEngineDetailScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: WEngineDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
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