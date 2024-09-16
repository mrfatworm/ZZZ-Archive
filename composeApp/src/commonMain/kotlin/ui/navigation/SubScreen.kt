/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents_list
import zzzarchive.composeapp.generated.resources.drivers_list
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.home
import zzzarchive.composeapp.generated.resources.setting
import zzzarchive.composeapp.generated.resources.w_engines_list

sealed class SubScreen(
    val route: String,
    val textRes: StringResource? = null,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : SubScreen(
        route = "homeOverview", textRes = Res.string.home
    )

    data object AgentsList : SubScreen(
        route = "agentsList", textRes = Res.string.agents_list
    )

    data object AgentDetail : SubScreen(
        route = "agentDetail/{agentId}",navArguments = listOf(navArgument("agentId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(agentId: String) = "agentDetail/${agentId}"
    }

    data object WeaponsList : SubScreen(
        route = "weaponsList", textRes = Res.string.w_engines_list
    )

    data object WeaponDetail : SubScreen(
        route = "weaponDetail/{weaponId}", navArguments = listOf(navArgument("weaponId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(weaponId: String) = "weaponDetail/${weaponId}"
    }

    data object DriversList : SubScreen(
        route = "driversList", textRes = Res.string.drivers_list
    )

    data object DriverDetail : SubScreen(
        route = "driverDetail/{driverId}", navArguments = listOf(navArgument("driverId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(driverId: String) = "driverDetail/${driverId}"
    }

    data object Setting : SubScreen(
        route = "settingOverview", textRes = Res.string.setting
    )

    data object Feedback : SubScreen(
        route = "feedbackOverview", textRes = Res.string.feedback
    )

}