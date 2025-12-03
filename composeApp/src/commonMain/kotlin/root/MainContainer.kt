/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ui.components.navigation.ModalNavigationDrawerContent
import ui.components.navigation.ZzzArchiveBottomNavigationBar
import ui.components.navigation.ZzzArchiveNavigationRail
import ui.navigation.NAV_BOTTOM_MAIN_FLOW
import ui.navigation.NAV_RAIL_MAIN_FLOW
import ui.navigation.NavActions
import ui.navigation.graph.MainNavGraph
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType

@Composable
fun MainContainer(rootNavActions: NavActions) {
    val mainFunNavController = rememberNavController()
    val mainFunNavActions =
        remember(mainFunNavController) {
            NavActions(mainFunNavController)
        }
    val navBackStackEntry by mainFunNavController.currentBackStackEntryAsState()

    val selectedDestination = navBackStackEntry?.destination

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val viewModel: MainContainerViewModel = koinViewModel()
    val isDark by viewModel.isDark.collectAsStateWithLifecycle()
    ModalNavigationDrawer(
        drawerContent = {
            ModalNavigationDrawerContent(
                selectedMainFlow = selectedDestination,
                navigationActions = mainFunNavActions,
                onDrawerClicked = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                onThemeChanged = {
                    coroutineScope.launch {
                        viewModel.setIsDarkTheme(!isDark)
                    }
                }
            )
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        val canNavigateBack = selectedDestination != null &&
            NAV_RAIL_MAIN_FLOW.none { mainFlow ->
                selectedDestination.hasRoute(mainFlow.route.startScreen::class)
            }
        MainFuncContent(
            mainFunNavController = mainFunNavController,
            mainNavActions = mainFunNavActions,
            rootNavActions = rootNavActions,
            selectedDestination = selectedDestination,
            canNavigateBack = canNavigateBack,
            onDrawerClicked = {
                coroutineScope.launch {
                    drawerState.open()
                }
            },
            onBackClick = {
                mainFunNavActions.back()
            },
            onThemeChanged = {
                coroutineScope.launch {
                    viewModel.setIsDarkTheme(!isDark)
                }
            }
        )
    }
}

@Composable
fun MainFuncContent(
    mainFunNavController: NavHostController,
    mainNavActions: NavActions,
    rootNavActions: NavActions,
    selectedDestination: NavDestination?,
    canNavigateBack: Boolean,
    onDrawerClicked: () -> Unit,
    onBackClick: () -> Unit,
    onThemeChanged: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.weight(1f)) {
            AnimatedVisibility(
                visible =
                    AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Medium ||
                        AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Expanded
            ) {
                ZzzArchiveNavigationRail(
                    modifier = Modifier
                        .fillMaxHeight(),
                    selectedMainFlow = selectedDestination,
                    navActions = mainNavActions,
                    canNavigateBack = canNavigateBack,
                    onDrawerClicked = onDrawerClicked,
                    onBackClick = onBackClick,
                    onThemeChanged = onThemeChanged
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainNavGraph(
                    modifier = Modifier.widthIn(max = AppTheme.size.maxContainerWidth),
                    navController = mainFunNavController,
                    navActions = mainNavActions,
                    rootNavActions = rootNavActions
                )
            }
        }
        val isBottomNavItem =
            NAV_BOTTOM_MAIN_FLOW.find { mainFlow ->
                selectedDestination?.hierarchy?.any {
                    it.hasRoute(route = mainFlow.route.startScreen::class)
                } == true
            }
        AnimatedVisibility(
            visible =
                AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact && isBottomNavItem != null
        ) {
            ZzzArchiveBottomNavigationBar(
                selectedMainFlow = selectedDestination,
                navigationActions = mainNavActions
            )
        }
    }
}
