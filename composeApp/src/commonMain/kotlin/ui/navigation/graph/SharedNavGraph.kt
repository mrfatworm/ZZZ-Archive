/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import app.agent.AgentDetailScreen
import app.agent.AgentsListScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.bangboo.BangbooDetailScreen
import app.bangboo.BangbooListScreen
import app.drive.DriveDetailScreen
import app.drive.DrivesListScreen
import ui.navigation.Screen
import ui.utils.ContentType
import app.wengine.WEngineDetailScreen
import app.wengine.WEnginesListScreen

fun NavGraphBuilder.sharedScreenDestination(
    navController: NavHostController,
    contentType: ContentType,
    navigateToTopLevelDestination: (Screen) -> Unit
) {
    composable(Screen.AgentsList.route) {
        AgentsListScreen(contentType, onAgentClick = {
            navController.navigate(
                Screen.AgentDetail.route
            )
        })
    }

    composable(Screen.AgentDetail.route) {
        AgentDetailScreen(onWEngineClick = { navController.navigate(Screen.WEngineDetail.route) })
    }

    composable(Screen.WEnginesList.route) {
        WEnginesListScreen(onWEngineClick = {
            navController.navigate(
                Screen.WEngineDetail.route
            )
        })
    }

    composable(Screen.WEngineDetail.route) {
        WEngineDetailScreen(onAgentClick = { navController.navigate(Screen.AgentDetail.route) })
    }

    composable(Screen.DrivesList.route) {
        DrivesListScreen(onDriveClick = {
            navController.navigate(
                Screen.DriveDetail.route
            )
        })
    }

    composable(Screen.DriveDetail.route) {
        DriveDetailScreen(onAgentClick = { navController.navigate(Screen.AgentDetail.route) })
    }

    composable(Screen.BangbooList.route) {
        BangbooListScreen(onBangbooClick = {
            navController.navigate(Screen.BangbooDetail.route)
        })
    }

    composable(Screen.BangbooDetail.route) {
        BangbooDetailScreen(onAgentClick = { navController.navigate(Screen.AgentDetail.route) })
    }

}