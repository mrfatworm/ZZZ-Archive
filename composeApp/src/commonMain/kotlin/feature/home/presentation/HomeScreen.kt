/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
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
    onBannerNavigate: (String) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val banner = uiState.value.banner
    val openBannerDialog = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    if (AppTheme.contentType == ContentType.Single) {
        HomeScreenSingle(uiState = uiState.value,
            onPixivTagChange = {
                coroutineScope.launch {
                    viewModel.fetchPixivTopic(it)
                }
            },
            onActionClicked = {
                openBannerDialog.value = true
            },
            onClosed = { id ->
                coroutineScope.launch {
                    viewModel.closeBannerAndIgnoreId(id)
                }
            })
    } else {
        HomeScreenDual(uiState = uiState.value,
            onAgentsOverviewClick = onAgentsOverviewClick,
            onWEnginesOverviewClick = onWEnginesOverviewClick,
            onBangbooOverviewClick = onBangbooOverviewClick,
            onDrivesOverviewClick = onDrivesOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onBangbooDetailClick = onBangbooDetailClick,
            onPixivTagChange = {
                coroutineScope.launch {
                    viewModel.fetchPixivTopic(it)
                }
            },
            onActionClicked = {
                openBannerDialog.value = true
            },
            onClosed = { id ->
                coroutineScope.launch {
                    viewModel.closeBannerAndIgnoreId(id)
                }
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
                    onBannerNavigate(it)
                },
                onDismiss = { openBannerDialog.value = false })
        }
    }
}

