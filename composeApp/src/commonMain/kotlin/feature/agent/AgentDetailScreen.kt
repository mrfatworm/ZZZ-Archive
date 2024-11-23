/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import feature.agent.components.AgentDetailScreenDual
import feature.agent.components.AgentDetailScreenSingle
import feature.agent.domain.AgentDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun AgentDetailScreen(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    wEngineClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    if (contentType == ContentType.Single) {
        AgentDetailScreenSingle(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick,
            wEngineClick = wEngineClick
        )
    } else {
        AgentDetailScreenDual(
            uiState = uiState,
            adaptiveLayoutType = adaptiveLayoutType,
            onBackClick = onBackClick,
            wEngineClick = wEngineClick
        )
    }
}