/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.graph.sharedNavGraph

fun NavGraphBuilder.bangbooNavGraph(navActions: NavActions) {
    navigation(
        route = MainFlow.Bangboo.route, startDestination = MainFlow.Bangboo.startScreen.route
    ) {
        sharedNavGraph(navActions)
    }
}