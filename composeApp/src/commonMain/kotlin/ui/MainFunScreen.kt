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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.component.ModalNavigationDrawerContent
import ui.navigation.component.ZzzArchiveBottomNavigationBar
import ui.navigation.component.ZzzArchiveNavigationRail
import ui.navigation.graph.MainNavGraph
import ui.theme.AppTheme
import ui.utils.ContentType
import ui.utils.NavigationType
import ui.utils.containerPadding
import ui.utils.contentPadding

@Composable
fun MainFunScreen(
    rootNavActions: NavActions, navigationType: NavigationType, contentType: ContentType
) {
    val mainFunNavController = rememberNavController()
    val mainFunNavActions = remember(mainFunNavController) {
        NavActions(mainFunNavController)
    }
    val navBackStackEntry by mainFunNavController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: MainFlow.Home.route

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
            navigationType = navigationType,
            contentType = contentType,
            onDrawerClicked = {
                scope.launch {
                    drawerState.open()
                }
            })
    }
}

@Composable
fun MainFunContent(
    mainFunNavController: NavHostController,
    mainNavActions: NavActions,
    rootNavActions: NavActions,
    selectedDestination: String,
    navigationType: NavigationType,
    contentType: ContentType,
    onDrawerClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f).containerPadding(navigationType, AppTheme.dimens),
            horizontalArrangement = horizontalParentGap(navigationType)
        ) {
            AnimatedVisibility(
                visible = navigationType == NavigationType.NAVIGATION_RAIL || navigationType == NavigationType.NAVIGATION_DRAWER
            ) {
                ZzzArchiveNavigationRail(
                    modifier = Modifier.fillMaxHeight()
                        .contentPadding(navigationType, AppTheme.dimens),
                    selectedDestination = selectedDestination,
                    navigationActions = mainNavActions,
                    onDrawerClicked = onDrawerClicked,
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                MainNavGraph(
                    modifier = Modifier.widthIn(max = AppTheme.dimens.maxContainerWidth)
                        .align(Alignment.TopCenter),
                    mainNavController = mainFunNavController,
                    contentType = contentType,
                    navigationType = navigationType,
                    mainNavActions = mainNavActions,
                    rootNavActions = rootNavActions
                )
            }
        }

        AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
            ZzzArchiveBottomNavigationBar(
                selectedDestination = selectedDestination, navigationActions = mainNavActions
            )
        }
    }
}

@Composable
private fun horizontalParentGap(navigationType: NavigationType) = Arrangement.spacedBy(
    when (navigationType) {
        NavigationType.NAVIGATION_DRAWER -> AppTheme.dimens.gapParentExpanded
        NavigationType.NAVIGATION_RAIL -> AppTheme.dimens.gapParentMedium
        NavigationType.BOTTOM_NAVIGATION -> 0.dp
    }
)