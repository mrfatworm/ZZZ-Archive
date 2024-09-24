/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.home.domain.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.utils.ContentType
import ui.utils.NavigationType

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    contentType: ContentType,
    navigationType: NavigationType,
    onAgentOverviewClick: () -> Unit,
    onWEngineOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit = {},
    onWEngineDetailClick: (Int) -> Unit = {},
    onDriveDetailClick: (Int) -> Unit = {},
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    if (contentType == ContentType.SINGLE) {
        HomeScreenSingle(
            uiState = uiState.value,
            navigationType = navigationType,
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDrivesOverviewClick = onDrivesOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriveDetailClick = onDriveDetailClick,
        )
    } else {
        HomeScreenDual(
            uiState = uiState.value,
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDrivesOverviewClick = onDrivesOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriveDetailClick = onDriveDetailClick,
        )
    }
}