/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.LifecycleResumeEffect
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.BannerDialog
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun HomeScreen(
    onAgentsOverviewClick: () -> Unit,
    onWEnginesOverviewClick: () -> Unit,
    onBangbooOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit,
    onWEngineDetailClick: (Int) -> Unit,
    onBangbooDetailClick: (Int) -> Unit,
    navigateTo: (String) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LifecycleResumeEffect(key1 = viewModel) {
        viewModel.onResume()
        onPauseOrDispose { }
    }
    HomeScreenContent(uiState, onAction = { actions ->
        when (actions) {
            HomeAction.ClickAgentsOverview -> onAgentsOverviewClick()
            HomeAction.ClickWEnginesOverview -> onWEnginesOverviewClick()
            HomeAction.ClickBangbooOverview -> onBangbooOverviewClick()
            HomeAction.ClickDrivesOverview -> onDrivesOverviewClick()

            is HomeAction.ClickAgent -> {
                onAgentDetailClick(actions.id)
            }

            is HomeAction.ClickWEngine -> {
                onWEngineDetailClick(actions.id)
            }

            is HomeAction.ClickBangboo -> {
                onBangbooDetailClick(actions.id)
            }

            is HomeAction.NavigateTo -> {
                navigateTo(actions.route)
            }

            else -> viewModel.onAction(actions)
        }
    })
}

@Composable
private fun HomeScreenContent(
    uiState: HomeState, onAction: (HomeAction) -> Unit
) {
    val banner = uiState.banner
    val openBannerDialog = remember { mutableStateOf(false) }
    if (AppTheme.contentType == ContentType.Single) {
        HomeScreenSingle(uiState = uiState, onAction = onAction, onOpenBannerDialog = {
            openBannerDialog.value = true
        })
    } else {
        HomeScreenDual(uiState = uiState, onAction = onAction, onOpenBannerDialog = {
            openBannerDialog.value = true
        })
    }
    when {
        openBannerDialog.value -> {
            BannerDialog(message = banner?.title ?: "",
                url = banner?.url ?: "",
                urlDesc = banner?.urlDesc ?: "",
                route = banner?.route ?: "",
                routeDesc = banner?.routeDesc ?: "",
                onNavigate = {
                    onAction(HomeAction.NavigateTo(it))
                },
                onDismiss = { openBannerDialog.value = false })
        }
    }
}

