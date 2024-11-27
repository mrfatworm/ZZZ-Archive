/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import androidx.compose.runtime.Composable
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
    val agentsList by viewModel.agentsList.collectAsStateWithLifecycle()
    val wEnginesList by viewModel.wEnginesList.collectAsStateWithLifecycle()
    val bangbooList by viewModel.bangbooList.collectAsStateWithLifecycle()
    val drivesList by viewModel.drivesList.collectAsStateWithLifecycle()

    WikiScreenSingle(
        agentsList = agentsList,
        wEnginesList = wEnginesList,
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