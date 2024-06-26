/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation

import androidx.navigation.NamedNavArgument
import org.jetbrains.compose.resources.DrawableResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_armor
import zzzarchive.composeapp.generated.resources.ic_charactor
import zzzarchive.composeapp.generated.resources.ic_feedback
import zzzarchive.composeapp.generated.resources.ic_home
import zzzarchive.composeapp.generated.resources.ic_setting
import zzzarchive.composeapp.generated.resources.ic_weapon

sealed class RootScreen(
    val route: String,
    val icon: DrawableResource,
    val text: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : RootScreen(
        route = "home", icon = Res.drawable.ic_home, text = "Home"
    )

    data object Characters : RootScreen(
        route = "characters", icon = Res.drawable.ic_charactor, text = "Character"
    )

    data object Weapons : RootScreen(
        route = "weapons", icon = Res.drawable.ic_weapon, text = "Weapon"
    )

    data object Artifacts : RootScreen(
        route = "echoes", icon = Res.drawable.ic_armor, text = "Artifact"
    )

    data object Setting : RootScreen(
        route = "setting", icon = Res.drawable.ic_setting, text = "Setting"
    )

    data object Feedback : RootScreen(
        route = "feedback", icon = Res.drawable.ic_feedback, text = "Feedback"
    )

}