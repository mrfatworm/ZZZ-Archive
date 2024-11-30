/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WikiScreen(
    onAgentsOverviewClick: () -> Unit,
    onWEnginesOverviewClick: () -> Unit,
    onBangbooOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit,
    onWEngineDetailClick: (Int) -> Unit,
    onBangbooDetailClick: (Int) -> Unit
) {
    val viewModel: WikiViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WikiScreenSingle(
        uiState = uiState,
        onAgentsOverviewClick = onAgentsOverviewClick,
        onWEnginesOverviewClick = onWEnginesOverviewClick,
        onBangbooOverviewClick = onBangbooOverviewClick,
        onDrivesOverviewClick = onDrivesOverviewClick,
        onAgentDetailClick = onAgentDetailClick,
        onWEngineDetailClick = onWEngineDetailClick,
        onBangbooDetailClick = onBangbooDetailClick,
    )
}