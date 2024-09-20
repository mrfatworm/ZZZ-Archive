/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import feedback.FeedbackScreen
import setting.SettingScreen
import ui.navigation.Screen
import ui.utils.ContentType

@Composable
fun SettingNavGraph(
    contentType: ContentType
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.Setting.route
    ) {
        composable(Screen.Setting.route) {
            SettingScreen(
                contentType = contentType,
                onFeedbackClicked = { navController.navigate(Screen.Feedback.route) })
        }
        composable(Screen.Feedback.route) {
            FeedbackScreen()
        }
    }
}