/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import ui.theme.Dimens
import ui.utils.ContentType
import ui.utils.NavigationType

@Composable
fun MainFunScreen(
    rootNavActions: NavActions,
    navigationType: NavigationType,
    contentType: ContentType
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
        MainFunContent(
            mainFunNavController = mainFunNavController,
            mainNavActions = mainFunNavActions,
            rootNavActions = rootNavActions,
            selectedDestination = selectedDestination,
            navType = navigationType,
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
    navType: NavigationType,
    contentType: ContentType,
    onDrawerClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), bottomBar = {
            AnimatedVisibility(visible = navType == NavigationType.BOTTOM_NAVIGATION) {
                ZzzArchiveBottomNavigationBar(
                    selectedDestination = selectedDestination, navigationActions = mainNavActions
                )
            }
        }, containerColor = AppTheme.colors.surface
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .containerPadding(navType, AppTheme.dimens, innerPadding),
            horizontalArrangement = Arrangement.spacedBy(
                when (navType) {
                    NavigationType.NAVIGATION_DRAWER -> AppTheme.dimens.gapParentExpanded
                    NavigationType.NAVIGATION_RAIL -> AppTheme.dimens.gapParentMedium
                    NavigationType.BOTTOM_NAVIGATION -> 0.dp
                }
            )
        ) {
            AnimatedVisibility(
                visible = navType == NavigationType.NAVIGATION_RAIL || navType == NavigationType.NAVIGATION_DRAWER
            ) {
                ZzzArchiveNavigationRail(
                    selectedDestination = selectedDestination,
                    navigationActions = mainNavActions,
                    onDrawerClicked = onDrawerClicked,
                )
            }
            MainNavGraph(
                modifier = Modifier.weight(1f),
                mainNavController = mainFunNavController,
                contentType = contentType,
                navigationType = navType,
                mainNavActions = mainNavActions,
                rootNavActions = rootNavActions
            )
        }
    }
}

fun Modifier.containerPadding(navType: NavigationType, dimens: Dimens, innerPadding: PaddingValues):Modifier {
    if (innerPadding.calculateTopPadding() > 0.dp) {
        return this.padding(innerPadding)
    } else {
        return this.padding(
            start = when (navType) {
                NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentStartExpanded
                NavigationType.NAVIGATION_RAIL -> dimens.paddingParentStartMedium
                NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
            },
            end = when (navType) {
                NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
                NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
                NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
            },
            top = when (navType) {
                NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
                NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
                NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
            },
            bottom = when (navType) {
                NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
                NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
                NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
            }
        )
    }
}