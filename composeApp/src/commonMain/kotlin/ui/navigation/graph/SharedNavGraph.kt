/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import feature.agent.presentation.AgentDetailScreen
import feature.agent.presentation.AgentsListScreen
import feature.bangboo.presentation.BangbooDetailScreen
import feature.bangboo.presentation.BangbooListScreen
import feature.drive.presentation.DrivesListScreen
import feature.feedback.presentation.FeedbackScreen
import feature.hoyolab.presentation.HoYoLabSyncScreen
import feature.setting.presentation.SettingScreen
import feature.wengine.presentation.WEngineDetailScreen
import feature.wengine.presentation.WEnginesListScreen
import ui.navigation.NavActions
import ui.navigation.Screen

fun NavGraphBuilder.sharedNavGraph(navActions: NavActions) {
    composable(Screen.AgentsList.route) {
        AgentsListScreen(onAgentClick = { id ->
            navActions.navigationToRoute(Screen.AgentDetail.createRoute(id))
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(route = Screen.AgentDetail.route, arguments = Screen.AgentDetail.navArguments) {
        AgentDetailScreen(wEngineClick = { id ->
            navActions.navigationToRoute(Screen.WEngineDetail.createRoute(id))
        }, onBackClick = { navActions.back() })
    }

    composable(Screen.WEnginesList.route) {
        WEnginesListScreen(onWEngineClick = { id ->
            navActions.navigationToRoute(
                Screen.WEngineDetail.createRoute(id)
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.WEngineDetail.route, arguments = Screen.WEngineDetail.navArguments) {
        WEngineDetailScreen(onBackClick = { navActions.back() })
    }

    composable(Screen.BangbooList.route) {
        BangbooListScreen(onBangbooClick = { id ->
            navActions.navigationToRoute(
                Screen.BangbooDetail.createRoute(id)
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.BangbooDetail.route, arguments = Screen.BangbooDetail.navArguments) {
        BangbooDetailScreen(onBackClick = { navActions.back() })
    }

    composable(Screen.DrivesList.route) {
        DrivesListScreen(onBackClick = {
            navActions.back()
        })
    }
    composable(Screen.Setting.route) {
        SettingScreen(
            onFeedbackClick = {
                navActions.navigationTo(Screen.Feedback)
            },
            onHoYoLabClick = {
                navActions.navigationTo(Screen.HoYoLabSync)
            }
        )
    }
    composable(Screen.Feedback.route) {
        FeedbackScreen {
            navActions.back()
        }
    }

    composable(Screen.HoYoLabSync.route) {
        HoYoLabSyncScreen(onBackClick = { navActions.back() }, navigateToFeedback = {
            navActions.navigationTo(Screen.Feedback)
        })

    }
}