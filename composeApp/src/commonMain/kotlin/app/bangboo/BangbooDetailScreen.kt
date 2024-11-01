/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.bangboo.compose.BangbooDetailScreenDual
import app.bangboo.compose.BangbooDetailScreenSingle
import app.bangboo.domain.BangbooDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun BangbooDetailScreen(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    val viewModel: BangbooDetailViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
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