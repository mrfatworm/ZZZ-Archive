/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import feature.function.FunctionScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.navigation.graph.sharedNavGraph
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.functionNavGraph(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navActions: NavActions
) {
    navigation(
        route = MainFlow.Function.route, startDestination = MainFlow.Function.startScreen.route
    ) {
        composable(Screen.Function.route) {
            FunctionScreen(adaptiveLayoutType = adaptiveLayoutType)
        }
        sharedNavGraph(contentType, adaptiveLayoutType, navActions)
    }
}