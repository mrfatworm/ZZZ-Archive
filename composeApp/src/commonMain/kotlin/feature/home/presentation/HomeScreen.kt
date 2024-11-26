/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.banner.data.BannerResponse
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ui.components.Banner
import ui.components.dialogs.BannerDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.view_detail

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
    onBannerNavigate: (String) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val banner = uiState.value.banner
    val agentsList by viewModel.agentsListState.collectAsStateWithLifecycle()
    val openBannerDialog = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    if (contentType == ContentType.Single) {
        HomeScreenSingle(uiState = uiState.value,
            adaptiveLayoutType = adaptiveLayoutType,
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
            agentsList = agentsList,
            adaptiveLayoutType = adaptiveLayoutType,
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

@Composable
internal fun ColumnScope.AnnouncementBanner(
    banner: BannerResponse?,
    onActionClicked: () -> Unit,
    onClosed: (Int) -> Unit
) {
    AnimatedVisibility(visible = banner != null) {
        banner?.let {
            Banner(modifier = Modifier.widthIn(max = AppTheme.dimens.maxContainerWidth),
                title = banner.title,
                bannerLevel = banner.getBannerLevel(),
                closable = banner.ignorable,
                actionTextRes = Res.string.view_detail,
                onActionClicked = onActionClicked,
                onClosed = {
                    onClosed(banner.id)
                })
        }
    }
}