/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Modifications by mrfatworm, 2024
 * customized,
 * remove navigationContentPosition & foldingDevice,
 * replace PermanentNavigationDrawer
 */

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import ui.navigation.DismissibleNavigationDrawerContent
import ui.navigation.ModalNavigationDrawerContent
import ui.navigation.RootScreen
import ui.navigation.ZzzArchiveBottomNavigationBar
import ui.navigation.ZzzArchiveNavigationActions
import ui.navigation.ZzzArchiveNavigationRail
import ui.navigation.graph.ZzzArchiveNavGraph
import ui.utils.ZzzArchiveContentType
import ui.utils.ZzzArchiveNavigationType


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun ZzzArchiveApp() {
    val windowSizeClass = calculateWindowSizeClass()
    val navigationType: ZzzArchiveNavigationType
    val contentType: ZzzArchiveContentType

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ZzzArchiveNavigationType.BOTTOM_NAVIGATION
            contentType = ZzzArchiveContentType.SINGLE
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = ZzzArchiveNavigationType.NAVIGATION_RAIL
            contentType = ZzzArchiveContentType.DUAL
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = ZzzArchiveNavigationType.NAVIGATION_DRAWER
            contentType = ZzzArchiveContentType.DUAL
        }

        else -> {
            navigationType = ZzzArchiveNavigationType.BOTTOM_NAVIGATION
            contentType = ZzzArchiveContentType.SINGLE
        }
    }
    MaterialTheme {
        KoinContext {
            ZzzArchiveNavigationWrapper(navigationType, contentType)
        }
    }
}

@Composable
fun ZzzArchiveNavigationWrapper(
    navigationType: ZzzArchiveNavigationType, contentType: ZzzArchiveContentType
) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        ZzzArchiveNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: RootScreen.Home.route

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            ModalNavigationDrawerContent(selectedDestination = selectedDestination,
                navigationActions = navigationActions,
                onDrawerClicked = {
                    scope.launch {
                        drawerState.close()
                    }
                })
        }, drawerState = drawerState, gesturesEnabled = false
    ) {
        RoverArchiveAppContent(navController = navController,
            navigationType = navigationType,
            contentType = contentType,
            selectedDestination = selectedDestination,
            navigationActions = navigationActions,
            onDrawerClicked = {
                scope.launch {
                    drawerState.open()
                }
            })
    }
}

@Composable
fun RoverArchiveAppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationType: ZzzArchiveNavigationType,
    selectedDestination: String,
    navigationActions: ZzzArchiveNavigationActions,
    contentType: ZzzArchiveContentType,
    onDrawerClicked: () -> Unit = {}
) {

    var drawerState by remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = navigationType == ZzzArchiveNavigationType.NAVIGATION_RAIL || navigationType == ZzzArchiveNavigationType.NAVIGATION_DRAWER
        ) {
            when (navigationType) {
                ZzzArchiveNavigationType.BOTTOM_NAVIGATION -> {}
                ZzzArchiveNavigationType.NAVIGATION_RAIL -> {
                    ZzzArchiveNavigationRail(
                        selectedDestination = selectedDestination,
                        navigationActions = navigationActions,
                        onDrawerClicked = onDrawerClicked,
                    )
                }

                ZzzArchiveNavigationType.NAVIGATION_DRAWER -> {
                    if (drawerState) {
                        DismissibleNavigationDrawerContent(selectedDestination = selectedDestination,
                            navigationActions = navigationActions,
                            onDrawerClicked = { drawerState = !drawerState })
                    } else {
                        ZzzArchiveNavigationRail(selectedDestination = selectedDestination,
                            navigationActions = navigationActions,
                            onDrawerClicked = { drawerState = !drawerState })
                    }

                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            ZzzArchiveNavGraph(
                modifier = Modifier.weight(1f),
                rootNavController = navController,
                contentType = contentType,
                navigationActions = navigationActions
            )
            AnimatedVisibility(visible = navigationType == ZzzArchiveNavigationType.BOTTOM_NAVIGATION) {
                ZzzArchiveBottomNavigationBar(
                    selectedDestination = selectedDestination, navigationActions = navigationActions
                )
            }
        }
    }
}