/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType

@Composable
fun WikiScreen(
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentsOverviewClick: () -> Unit,
    onWEnginesOverviewClick: () -> Unit,
    onBangbooOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit,
    onWEngineDetailClick: (Int) -> Unit,
    onBangbooDetailClick: (Int) -> Unit
) {
    val viewModel: WikiViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val agentsList by viewModel.agentsList.collectAsStateWithLifecycle()
    val bangbooList by viewModel.bangbooList.collectAsStateWithLifecycle()
    val drivesList by viewModel.drivesList.collectAsStateWithLifecycle()

    WikiScreenSingle(
        uiState = uiState.value,
        agentsList = agentsList,
        bangbooList = bangbooList,
        drivesList = drivesList,
        adaptiveLayoutType = adaptiveLayoutType,
        onAgentsOverviewClick = onAgentsOverviewClick,
        onWEnginesOverviewClick = onWEnginesOverviewClick,
        onBangbooOverviewClick = onBangbooOverviewClick,
        onDrivesOverviewClick = onDrivesOverviewClick,
        onAgentDetailClick = onAgentDetailClick,
        onWEngineDetailClick = onWEngineDetailClick,
        onBangbooDetailClick = onBangbooDetailClick,
    )
}