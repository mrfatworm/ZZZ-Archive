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

fun NavGraphBuilder.settingNavGraph(navActions: NavActions) {
    navigation(
        route = MainFlow.Setting.route, startDestination = MainFlow.Setting.startScreen.route
    ) {
        sharedNavGraph(navActions)
    }
}