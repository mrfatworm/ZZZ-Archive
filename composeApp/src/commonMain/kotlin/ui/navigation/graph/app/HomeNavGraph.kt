/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import feature.home.presentation.HomeScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.navigation.graph.sharedNavGraph

fun NavGraphBuilder.homeNavGraph(navActions: NavActions) {
    navigation(
        route = MainFlow.Home.route, startDestination = MainFlow.Home.startScreen.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAgentsOverviewClick = { navActions.navigationToMainScreen(MainFlow.Agent) },
                onWEnginesOverviewClick = { navActions.navigationToMainScreen(MainFlow.WEngine) },
                onBangbooOverviewClick = { navActions.navigationToMainScreen(MainFlow.Bangboo) },
                onDrivesOverviewClick = { navActions.navigationToMainScreen(MainFlow.Drive) },
                onAgentDetailClick = { id ->
                    navActions.navigationToRoute(
                        Screen.AgentDetail.createRoute(
                            id
                        )
                    )
                }, onWEngineDetailClick = { id ->
                    navActions.navigationToRoute(
                        Screen.WEngineDetail.createRoute(
                            id
                        )
                    )
                },
                onBangbooDetailClick = { id ->
                    navActions.navigationToRoute(
                        Screen.BangbooDetail.createRoute(
                            id
                        )
                    )
                },
                navigateTo = { route ->
                    navActions.navigationToRoute(route)
                }
            )
        }
        sharedNavGraph(navActions)
    }
}