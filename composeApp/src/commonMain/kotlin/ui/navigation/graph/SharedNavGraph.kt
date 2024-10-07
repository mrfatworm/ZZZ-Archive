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
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navActions: NavActions
) {
    composable(Screen.AgentsList.route) {
        AgentsListScreen(contentType, adaptiveLayoutType, onAgentClick = {
            navActions.navigationTo(
                Screen.AgentDetail
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.AgentDetail.route) {
        AgentDetailScreen(onWEngineClick = { navActions.navigationTo(Screen.WEngineDetail) })
    }

    composable(Screen.WEnginesList.route) {
        WEnginesListScreen(onWEngineClick = {
            navActions.navigationTo(
                Screen.WEngineDetail
            )
        })
    }

    composable(Screen.WEngineDetail.route) {
        WEngineDetailScreen(onAgentClick = { navActions.navigationTo(Screen.AgentDetail) })
    }

    composable(Screen.DrivesList.route) {
        DrivesListScreen(onDriveClick = {
            navActions.navigationTo(
                Screen.DriveDetail
            )
        })
    }

    composable(Screen.DriveDetail.route) {
        DriveDetailScreen(onAgentClick = { navActions.navigationTo(Screen.AgentDetail) })
    }

    composable(Screen.BangbooList.route) {
        BangbooListScreen(onBangbooClick = {
            navActions.navigationTo(Screen.BangbooDetail)
        })
    }

    composable(Screen.BangbooDetail.route) {
        BangbooDetailScreen(onAgentClick = { navActions.navigationTo(Screen.AgentDetail) })
    }
}