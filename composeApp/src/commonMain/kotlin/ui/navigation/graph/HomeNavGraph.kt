/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.home.HomeScreen
import ui.navigation.MainFlow
import ui.navigation.Screen
import ui.utils.ContentType
import ui.utils.AdaptiveLayoutType

@Composable
fun HomeNavHost(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navigateToTopLevelDestination: (Screen) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        if (contentType == ContentType.Dual) {
            composable(Screen.Home.route) {
                HomeScreen(contentType = contentType,
                    adaptiveLayoutType = adaptiveLayoutType,
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
                    adaptiveLayoutType = adaptiveLayoutType,
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