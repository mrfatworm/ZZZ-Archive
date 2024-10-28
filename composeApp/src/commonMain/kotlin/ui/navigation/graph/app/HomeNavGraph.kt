/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.home.HomeScreen
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.navigation.graph.sharedComposable
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.homeNavGraph(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navActions: NavActions
) {
    navigation(
        route = MainFlow.Home.route, startDestination = MainFlow.Home.startScreen.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(contentType = contentType,
                adaptiveLayoutType = adaptiveLayoutType,
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
                onBangbooDetailClick = { navActions.navigationTo(Screen.BangbooDetail) },
                onDriveDetailClick = { navActions.navigationTo(Screen.DriveDetail) })
        }
        sharedComposable(contentType, adaptiveLayoutType, navActions)
    }
}