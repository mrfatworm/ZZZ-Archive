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
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun HomeNavHost(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navigateToTopLevelDestination: (Screen) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(contentType = contentType,
                adaptiveLayoutType = adaptiveLayoutType,
                onAgentsOverviewClick = { navigateToTopLevelDestination(MainFlow.Agent) },
                onWEnginesOverviewClick = { navigateToTopLevelDestination(MainFlow.WEngine) },
                onBangbooOverviewClick = { navigateToTopLevelDestination(MainFlow.Bangboo) },
                onDrivesOverviewClick = { navigateToTopLevelDestination(MainFlow.Drive) },
                onAgentDetailClick = { navController.navigate(Screen.AgentDetail.route) },
                onWEngineDetailClick = { navController.navigate(Screen.WEngineDetail.route) },
                onBangbooDetailClick = { navController.navigate(Screen.BangbooDetail.route) },
                onDriveDetailClick = { navController.navigate(Screen.DriveDetail.route) })
        }
        sharedScreenDestination(navController, contentType, navigateToTopLevelDestination)
    }
}