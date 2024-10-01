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
import app.feedback.FeedbackScreen
import app.function.FunctionScreen
import app.wiki.WikiScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    mainNavActions: NavActions,
    rootNavActions: NavActions,
) {
    NavHost(
        modifier = modifier,
        navController = mainNavController,
        startDestination = MainFlow.Home.route,
    ) {
        composable(MainFlow.Home.route) {
            HomeNavHost(
                contentType = contentType, adaptiveLayoutType = adaptiveLayoutType,
                navigateToTopLevelDestination = { mainNavActions.navigationToTop(it) })
        }
        composable(MainFlow.Agent.route) {
            AgentNavGraph(contentType, navigateToTopLevelDestination = {
                mainNavActions.navigationToTop(it)
            })
        }
        composable(MainFlow.WEngine.route) {
            WEngineNavGraph(contentType, navigateToTopLevelDestination = {
                mainNavActions.navigationToTop(it)
            })
        }
        composable(MainFlow.Drive.route) {
            DriveNavGraph(contentType, navigateToTopLevelDestination = {
                mainNavActions.navigationToTop(it)
            })
        }
        composable(MainFlow.Bangboo.route) {
            BangbooNavGraph(contentType, navigateToTopLevelDestination = {
                mainNavActions.navigationToTop(it)
            })
        }
        composable(MainFlow.Setting.route) {
            SettingNavGraph(contentType = contentType)
        }
        composable(MainFlow.Feedback.route) {
            FeedbackScreen()
        }
        composable(MainFlow.Wiki.route) {
            WikiScreen(contentType = contentType,
                onAgentOverviewClick = { },
                onWEngineOverviewClick = { },
                onDrivesOverviewClick = { })
        }
        composable(MainFlow.Function.route) {
            FunctionScreen(contentType = contentType)
        }
    }
}
