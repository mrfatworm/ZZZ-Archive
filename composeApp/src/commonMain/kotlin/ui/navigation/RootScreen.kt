/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.navigation

import androidx.navigation.NamedNavArgument
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.bangboo
import zzzarchive.composeapp.generated.resources.drivers
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.home
import zzzarchive.composeapp.generated.resources.ic_bangboo
import zzzarchive.composeapp.generated.resources.ic_cd
import zzzarchive.composeapp.generated.resources.ic_happy
import zzzarchive.composeapp.generated.resources.ic_home
import zzzarchive.composeapp.generated.resources.ic_people
import zzzarchive.composeapp.generated.resources.ic_setting
import zzzarchive.composeapp.generated.resources.ic_w_engine
import zzzarchive.composeapp.generated.resources.setting
import zzzarchive.composeapp.generated.resources.w_engines

sealed class RootScreen(
    val route: String,
    val iconRes: DrawableResource,
    val textRes: StringResource,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : RootScreen(
        route = "home", iconRes = Res.drawable.ic_home, textRes = Res.string.home
    )

    data object Agents : RootScreen(
        route = "characters", iconRes = Res.drawable.ic_people, textRes = Res.string.agents
    )

    data object Weapons : RootScreen(
        route = "weapons", iconRes = Res.drawable.ic_w_engine, textRes = Res.string.w_engines
    )

    data object Drivers : RootScreen(
        route = "echoes", iconRes = Res.drawable.ic_cd, textRes = Res.string.drivers
    )

    data object Bangboo : RootScreen(
        route = "bangboo", iconRes = Res.drawable.ic_bangboo, textRes = Res.string.bangboo
    )

    data object Setting : RootScreen(
        route = "setting", iconRes = Res.drawable.ic_setting, textRes = Res.string.setting
    )

    data object Feedback : RootScreen(
        route = "feedback", iconRes = Res.drawable.ic_happy, textRes = Res.string.feedback
    )

}