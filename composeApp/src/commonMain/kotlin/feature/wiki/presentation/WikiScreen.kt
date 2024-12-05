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

    WikiScreenContent(uiState) { actions ->
        when (actions) {
            WikiAction.ClickAgentsOverview -> onAgentsOverviewClick()
            WikiAction.ClickWEnginesOverview -> onWEnginesOverviewClick()
            WikiAction.ClickBangbooOverview -> onBangbooOverviewClick()
            WikiAction.ClickDrivesOverview -> onDrivesOverviewClick()
            is WikiAction.ClickAgent -> {
                onAgentDetailClick(actions.id)
            }

            is WikiAction.ClickWEngine -> {
                onWEngineDetailClick(actions.id)
            }

            is WikiAction.ClickBangboo -> {
                onBangbooDetailClick(actions.id)
            }
        }
    }
}

@Composable
private fun WikiScreenContent(
    uiState: WikiState, onAction: (WikiAction) -> Unit
) {
    WikiScreenSingle(uiState = uiState, onAction)
}