/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.agent.AgentDetailScreen
import app.agent.AgentsListScreen
import app.bangboo.BangbooDetailScreen
import app.bangboo.BangbooListScreen
import app.drive.DriveDetailScreen
import app.drive.DrivesListScreen
import app.wengine.WEngineDetailScreen
import app.wengine.WEnginesListScreen
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.sharedComposable(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, navActions: NavActions
) {
    composable(Screen.AgentsList.route) {
        AgentsListScreen(contentType, adaptiveLayoutType, onAgentClick = { id ->
            navActions.navigationToRoute(Screen.AgentDetail.createRoute(id))
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(route = Screen.AgentDetail.route, arguments = Screen.AgentDetail.navArguments) {
        AgentDetailScreen(contentType, adaptiveLayoutType, onBackClick = { navActions.back() })
    }

    composable(Screen.WEnginesList.route) {
        WEnginesListScreen(adaptiveLayoutType = adaptiveLayoutType, onWEngineClick = { id ->
            navActions.navigationToRoute(
                Screen.WEngineDetail.createRoute(id)
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.WEngineDetail.route, arguments = Screen.WEngineDetail.navArguments) {
        WEngineDetailScreen(contentType, adaptiveLayoutType, onBackClick = { navActions.back() })
    }

    composable(Screen.BangbooList.route) {
        BangbooListScreen(adaptiveLayoutType = adaptiveLayoutType, onBangbooClick = {
            navActions.navigationTo(Screen.BangbooDetail)
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.BangbooDetail.route) {
        BangbooDetailScreen(onAgentClick = { navActions.navigationTo(Screen.AgentDetail) })
    }

    composable(Screen.DrivesList.route) {
        DrivesListScreen(adaptiveLayoutType = adaptiveLayoutType, onDriveClick = {
            navActions.navigationTo(
                Screen.DriveDetail
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.DriveDetail.route) {
        DriveDetailScreen(onAgentClick = { navActions.navigationTo(Screen.AgentDetail) })
    }
}