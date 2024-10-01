/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.home.domain.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun HomeScreen(
    contentType: ContentType,
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
    val viewModel: HomeViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    if (contentType == ContentType.Single) {
        HomeScreenSingle(
            uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
        )
    } else {
        HomeScreenDual(
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
}