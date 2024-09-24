/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.home.compose.HomeScreen
import ui.navigation.MainFlow
import ui.navigation.Screen
import ui.utils.ContentType
import ui.utils.NavigationType

@Composable
fun HomeNavHost(
    contentType: ContentType,
    navigationType: NavigationType,
    navigateToTopLevelDestination: (Screen) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        if (contentType == ContentType.DUAL) {
            composable(Screen.Home.route) {
                HomeScreen(contentType = contentType,
                    navigationType = navigationType,
                    onAgentOverviewClick = { navigateToTopLevelDestination(MainFlow.Agent) },
                    onWEngineOverviewClick = { navigateToTopLevelDestination(MainFlow.WEngine) },
                    onDrivesOverviewClick = { navigateToTopLevelDestination(MainFlow.Drive) },
                    onAgentDetailClick = { navController.navigate(Screen.AgentDetail.route) },
                    onWEngineDetailClick = { navController.navigate(Screen.WEngineDetail.route) },
                    onDriveDetailClick = { navController.navigate(Screen.DriveDetail.route) })
            }
        } else {
            composable(Screen.Home.route) {
                HomeScreen(contentType = contentType,
                    navigationType = navigationType,
                    onAgentOverviewClick = { navController.navigate(Screen.AgentsList.route) },
                    onWEngineOverviewClick = { navController.navigate(Screen.WEnginesList.route) },
                    onDrivesOverviewClick = { navController.navigate(Screen.DrivesList.route) },
                    onAgentDetailClick = { navController.navigate(Screen.AgentDetail.route) },
                    onWEngineDetailClick = { navController.navigate(Screen.WEngineDetail.route) },
                    onDriveDetailClick = { navController.navigate(Screen.DriveDetail.route) })
            }
        }
        sharedScreenDestination(navController, contentType, navigateToTopLevelDestination)
    }
}