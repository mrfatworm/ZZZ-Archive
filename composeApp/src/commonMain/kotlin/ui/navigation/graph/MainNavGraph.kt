/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
import ui.navigation.graph.app.feedbackNavGraph
import ui.navigation.graph.app.functionNavGraph
import ui.navigation.graph.app.homeNavGraph
import ui.navigation.graph.app.settingNavGraph
import ui.navigation.graph.app.wEngineNavGraph
import ui.navigation.graph.app.wikiNavGraph
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navActions: NavActions,
    rootNavActions: NavActions,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainFlow.Home.route,
    ) {
        homeNavGraph(contentType, adaptiveLayoutType, navActions)
        agentNavGraph(contentType, adaptiveLayoutType, navActions)
        wEngineNavGraph(contentType, adaptiveLayoutType, navActions)
        bangbooNavGraph(contentType, adaptiveLayoutType, navActions)
        driveNavGraph(contentType, adaptiveLayoutType, navActions)
        wikiNavGraph(contentType, adaptiveLayoutType, navActions)
        functionNavGraph(contentType, adaptiveLayoutType, navActions)
        settingNavGraph(contentType, adaptiveLayoutType, navActions)
        feedbackNavGraph()
    }
}


