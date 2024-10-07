/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.feedback.FeedbackScreen
import app.setting.SettingScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.utils.ContentType

fun NavGraphBuilder.settingNavGraph(
    contentType: ContentType,
    navActions: NavActions
) {
    navigation(
        route = MainFlow.Setting.route, startDestination = MainFlow.Setting.startScreen.route
    ) {
        composable(Screen.Setting.route) {
            SettingScreen(contentType = contentType,
                onFeedbackClicked = { navActions.navigationTo(Screen.Feedback) })
        }
        composable(Screen.Feedback.route) {
            FeedbackScreen()
        }
    }
}