/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.feedback.FeedbackScreen
import ui.navigation.MainFlow
import ui.navigation.Screen

fun NavGraphBuilder.feedbackNavGraph() {
    navigation(
        route = MainFlow.Feedback.route, startDestination = MainFlow.Feedback.startScreen.route
    ) {
        composable(Screen.Feedback.route) {
            FeedbackScreen()
        }
    }
}