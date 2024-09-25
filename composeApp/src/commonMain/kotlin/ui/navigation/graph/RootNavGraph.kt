/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.splash.SplashScreen
import ui.MainFunScreen
import ui.navigation.NavActions
import ui.navigation.RootFlow
import ui.utils.ContentType
import ui.utils.NavigationType

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    rootNavActions: NavActions,
    navigationType: NavigationType,
    contentType: ContentType
) {
    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = RootFlow.Splash.route,
    ) {
        composable(RootFlow.Splash.route) {
            SplashScreen(contentType = contentType,
                startMainFlow = { rootNavActions.navigationToTop(RootFlow.ZzzArchive) })
        }
        composable(RootFlow.ZzzArchive.route) {
            MainFunScreen(
                rootNavActions = rootNavActions,
                navigationType = navigationType,
                contentType = contentType
            )
        }
    }
}
