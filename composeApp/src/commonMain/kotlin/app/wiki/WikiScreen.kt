/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wiki

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.wiki.domain.WikiViewModel
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
    onBangbooDetailClick: (Int) -> Unit,
    onDriveDetailClick: (Int) -> Unit,
) {
    val viewModel: WikiViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    WikiScreenSingle(
        uiState = uiState.value,
        adaptiveLayoutType = adaptiveLayoutType,
        onAgentsOverviewClick = onAgentsOverviewClick,
        onWEnginesOverviewClick = onWEnginesOverviewClick,
        onBangbooOverviewClick = onBangbooOverviewClick,
        onDrivesOverviewClick = onDrivesOverviewClick,
        onAgentDetailClick = onAgentDetailClick,
        onWEngineDetailClick = onWEngineDetailClick,
        onBangbooDetailClick = onBangbooDetailClick,
        onDriveDetailClick = onDriveDetailClick,
    )
}