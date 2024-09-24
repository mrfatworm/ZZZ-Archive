/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.bangboo
import zzzarchive.composeapp.generated.resources.drives
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.home
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.setting
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.w_engines


sealed class Screen(
    val route: String,
    val iconRes: DrawableResource = Res.drawable.ic_help,
    val textRes: StringResource = Res.string.unknown,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen(
        route = "home", textRes = Res.string.home
    )

    data object AgentsList : Screen(
        route = "agentsList", textRes = Res.string.agents
    )

    data object AgentDetail : Screen(
        route = "agentDetail/{agentId}", navArguments = listOf(navArgument("agentId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(agentId: String) = "agentDetail/${agentId}"
    }

    data object WEnginesList : Screen(
        route = "wEnginesList", textRes = Res.string.w_engines
    )

    data object WEngineDetail : Screen(
        route = "wEngineDetail/{wEngineId}", navArguments = listOf(navArgument("wEngineId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(wEngineId: String) = "wEngineDetail/${wEngineId}"
    }

    data object DriversList : Screen(
        route = "driversList", textRes = Res.string.drives
    )

    data object DriverDetail : Screen(
        route = "driverDetail/{driverId}", navArguments = listOf(navArgument("driverId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(driverId: String) = "driverDetail/${driverId}"
    }

    data object BangbooList : Screen(
        route = "bangBooList", textRes = Res.string.bangboo
    )

    data object BangbooDetail : Screen(
        route = "bangbooDetail/{bangBooId}", navArguments = listOf(navArgument("bangbooId") {
            type = NavType.StringType
        })
    )

    data object Setting : Screen(
        route = "setting", textRes = Res.string.setting
    )

    data object Feedback : Screen(
        route = "feedback", textRes = Res.string.feedback
    )

}