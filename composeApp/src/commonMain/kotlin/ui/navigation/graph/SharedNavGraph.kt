/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import feature.agent.presentation.AgentDetailScreen
import feature.agent.presentation.AgentsListScreen
import feature.bangboo.BangbooDetailScreen
import feature.bangboo.BangbooListScreen
import feature.drive.presentation.DrivesListScreen
import feature.setting.FeedbackScreen
import feature.setting.SettingScreen
import feature.wengine.presentation.WEngineDetailScreen
import feature.wengine.presentation.WEnginesListScreen
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.sharedNavGraph(
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
        AgentDetailScreen(contentType, adaptiveLayoutType, wEngineClick = { id ->
            navActions.navigationToRoute(Screen.WEngineDetail.createRoute(id))
        }, onBackClick = { navActions.back() })
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
        BangbooListScreen(adaptiveLayoutType = adaptiveLayoutType, onBangbooClick = { id ->
            navActions.navigationToRoute(
                Screen.BangbooDetail.createRoute(id)
            )
        }, onBackClick = {
            navActions.back()
        })
    }

    composable(Screen.BangbooDetail.route, arguments = Screen.BangbooDetail.navArguments) {
        BangbooDetailScreen(contentType, adaptiveLayoutType, onBackClick = { navActions.back() })
    }

    composable(Screen.DrivesList.route) {
        DrivesListScreen(adaptiveLayoutType = adaptiveLayoutType, onBackClick = {
            navActions.back()
        })
    }
    composable(Screen.Setting.route) {
        SettingScreen(
            contentType = contentType,
            adaptiveLayoutType = adaptiveLayoutType,
            onFeedbackClick = {
                navActions.navigationTo(Screen.Feedback)
            }
        )
    }
    composable(Screen.Feedback.route) {
        FeedbackScreen(adaptiveLayoutType) {
            navActions.back()
        }
    }
}