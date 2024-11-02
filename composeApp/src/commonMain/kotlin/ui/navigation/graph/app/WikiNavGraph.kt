/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.wiki.WikiScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.navigation.graph.sharedComposable
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.wikiNavGraph(
    contentType: ContentType, adaptiveLayoutType: AdaptiveLayoutType, navActions: NavActions
) {
    navigation(
        route = MainFlow.Wiki.route, startDestination = MainFlow.Wiki.startScreen.route
    ) {
        composable(Screen.Wiki.route) {
            WikiScreen(
                adaptiveLayoutType = adaptiveLayoutType,
                onAgentsOverviewClick = { navActions.navigationTo(Screen.AgentsList) },
                onWEnginesOverviewClick = { navActions.navigationTo(Screen.WEnginesList) },
                onBangbooOverviewClick = { navActions.navigationTo(Screen.BangbooList) },
                onDrivesOverviewClick = { navActions.navigationTo(Screen.DrivesList) },
                onAgentDetailClick = { id ->
                    navActions.navigationToRoute(Screen.AgentDetail.createRoute(id))
                }, onWEngineDetailClick = { id ->
                    navActions.navigationToRoute(Screen.WEngineDetail.createRoute(id))
                }, onBangbooDetailClick = { id ->
                    navActions.navigationToRoute(Screen.BangbooDetail.createRoute(id))
                })
        }
        sharedComposable(contentType, adaptiveLayoutType, navActions)
    }
}