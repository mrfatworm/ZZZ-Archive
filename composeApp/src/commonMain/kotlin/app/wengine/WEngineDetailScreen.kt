/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.wengine.compose.WEngineDetailScreenDual
import app.wengine.compose.WEngineDetailScreenSingle
import app.wengine.domain.WEngineDetailViewModel
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