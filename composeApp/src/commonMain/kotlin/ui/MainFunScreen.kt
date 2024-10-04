/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.component.ModalNavigationDrawerContent
import ui.navigation.component.ZzzArchiveBottomNavigationBar
import ui.navigation.component.ZzzArchiveNavigationRail
import ui.navigation.graph.MainNavGraph
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import ui.utils.containerPadding
import ui.utils.contentPadding

@Composable
fun MainFunScreen(
    rootNavActions: NavActions, adaptiveLayoutType: AdaptiveLayoutType, contentType: ContentType
) {
    val mainFunNavController = rememberNavController()
    val mainFunNavActions = remember(mainFunNavController) {
        NavActions(mainFunNavController)
    }
    val navBackStackEntry by mainFunNavController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: MainFlow.Home.route

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val viewModel: MainFunViewModel = koinViewModel()
    val isDark by viewModel.isDark.collectAsState()
    var isDarkComposeState by AppTheme.isDark

    ModalNavigationDrawer(
        drawerContent = {
            ModalNavigationDrawerContent(selectedDestination = selectedDestination,
                navigationActions = mainFunNavActions,
                onDrawerClicked = {
                    scope.launch {
                        drawerState.close()
                    }
                })
        }, drawerState = drawerState, gesturesEnabled = false
    ) {
        MainFunContent(mainFunNavController = mainFunNavController,
            mainNavActions = mainFunNavActions,
            rootNavActions = rootNavActions,
            selectedDestination = selectedDestination,
            adaptiveLayoutType = adaptiveLayoutType,
            contentType = contentType,
            onDrawerClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onThemeChanged = {
                viewModel.setIsDarkTheme(!isDark)
                isDarkComposeState = !isDark
            })
    }
}

@Composable
fun MainFunContent(
    mainFunNavController: NavHostController,
    mainNavActions: NavActions,
    rootNavActions: NavActions,
    selectedDestination: String,
    adaptiveLayoutType: AdaptiveLayoutType,
    contentType: ContentType,
    onDrawerClicked: () -> Unit,
    onThemeChanged: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f).containerPadding(adaptiveLayoutType, AppTheme.dimens),
            horizontalArrangement = horizontalParentGap(adaptiveLayoutType)
        ) {
            AnimatedVisibility(
                visible = adaptiveLayoutType == AdaptiveLayoutType.Medium || adaptiveLayoutType == AdaptiveLayoutType.Expanded
            ) {
                ZzzArchiveNavigationRail(
                    modifier = Modifier.fillMaxHeight()
                        .contentPadding(adaptiveLayoutType, AppTheme.dimens),
                    selectedDestination = selectedDestination,
                    navigationActions = mainNavActions,
                    onDrawerClicked = onDrawerClicked,
                    onThemeChanged = onThemeChanged
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                MainNavGraph(
                    modifier = Modifier.widthIn(max = AppTheme.dimens.maxContainerWidth)
                        .align(Alignment.TopCenter),
                    mainNavController = mainFunNavController,
                    contentType = contentType,
                    adaptiveLayoutType = adaptiveLayoutType,
                    mainNavActions = mainNavActions,
                    rootNavActions = rootNavActions
                )
            }
        }

        AnimatedVisibility(visible = adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzArchiveBottomNavigationBar(
                selectedDestination = selectedDestination, navigationActions = mainNavActions
            )
        }
    }
}

@Composable
private fun horizontalParentGap(adaptiveLayoutType: AdaptiveLayoutType) = Arrangement.spacedBy(
    when (adaptiveLayoutType) {
        AdaptiveLayoutType.Expanded -> AppTheme.dimens.gapParentExpanded
        AdaptiveLayoutType.Medium -> AppTheme.dimens.gapParentMedium
        AdaptiveLayoutType.Compact -> 0.dp
    }
)