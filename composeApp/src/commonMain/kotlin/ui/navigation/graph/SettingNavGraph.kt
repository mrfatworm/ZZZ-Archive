/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import feedback.FeedbackScreen
import setting.SettingScreen
import ui.navigation.SubScreen
import ui.utils.ZzzArchiveContentType

@Composable
fun SettingNavGraph(
    contentType: ZzzArchiveContentType
) {
    val settingNavController = rememberNavController()
    NavHost(
        navController = settingNavController, startDestination = SubScreen.Setting.route
    ) {
        composable(SubScreen.Setting.route) {
            SettingScreen(
                contentType = contentType,
                onFeedbackClicked = { settingNavController.navigate(SubScreen.Feedback.route) })
        }
        composable(SubScreen.Feedback.route) {
            FeedbackScreen()
        }
    }
}