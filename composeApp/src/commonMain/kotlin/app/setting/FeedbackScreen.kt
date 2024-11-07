/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: MIT License
 */

package app.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.setting.compose.FeedbackScreenCompact
import app.setting.compose.FeedbackScreenMedium
import app.setting.domain.FeedbackViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun FeedbackScreen(adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit) {
    val viewModel: FeedbackViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            FeedbackScreenCompact(uiState.value, onBackClick = onBackClick)
        } else {
            FeedbackScreenMedium(uiState.value, adaptiveLayoutType, onBackClick)
        }
    }
}
