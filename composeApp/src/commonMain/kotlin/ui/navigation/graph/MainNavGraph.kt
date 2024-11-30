/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.graph.app.agentNavGraph
import ui.navigation.graph.app.bangbooNavGraph
import ui.navigation.graph.app.driveNavGraph
import ui.navigation.graph.app.functionNavGraph
import ui.navigation.graph.app.homeNavGraph
import ui.navigation.graph.app.settingNavGraph
import ui.navigation.graph.app.wEngineNavGraph
import ui.navigation.graph.app.wikiNavGraph

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navActions: NavActions,
    rootNavActions: NavActions
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainFlow.Home.route
    ) {
        homeNavGraph(navActions)
        agentNavGraph(navActions)
        wEngineNavGraph(navActions)
        bangbooNavGraph(navActions)
        driveNavGraph(navActions)
        wikiNavGraph(navActions)
        functionNavGraph(navActions)
        settingNavGraph(navActions)
    }
}


