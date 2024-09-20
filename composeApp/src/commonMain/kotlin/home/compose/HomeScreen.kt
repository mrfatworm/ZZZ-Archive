/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import home.domain.ZzzViewModel
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
    onDriversOverviewClick: () -> Unit,
    onAgentDetailClick: (Long) -> Unit = {},
    onWEngineDetailClick: (Long) -> Unit = {},
    onDriverDetailClick: (Long) -> Unit = {},
) {
    val viewModel = koinViewModel<ZzzViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(true) {
        viewModel.getActivityTitle()
    }
    if (contentType == ContentType.SINGLE) {
        HomeScreenSingle(
            uiState = uiState.value,
            navigationType = navigationType,
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDriversOverviewClick = onDriversOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriverDetailClick = onDriverDetailClick,
        )
    } else {
        HomeScreenDual(
            uiState = uiState.value,
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDriversOverviewClick = onDriversOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriverDetailClick = onDriverDetailClick,
        )
    }
}