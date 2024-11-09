/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.splash.SplashScreen
import mainfunc.MainFuncScreen
import ui.navigation.NavActions
import ui.navigation.RootFlow
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    rootNavActions: NavActions,
    adaptiveLayoutType: AdaptiveLayoutType,
    contentType: ContentType
) {
    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = RootFlow.Splash.route,
    ) {
        composable(RootFlow.Splash.route) {
            SplashScreen(contentType = contentType,
                startMainFlow = { rootNavActions.popAndNavigation(RootFlow.ZzzArchive) })
        }
        composable(RootFlow.ZzzArchive.route) {
            MainFuncScreen(
                rootNavActions = rootNavActions,
                adaptiveLayoutType = adaptiveLayoutType,
                contentType = contentType

            )
        }
    }
}
