/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.bangboo
import zzzarchive.composeapp.generated.resources.drives
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.function
import zzzarchive.composeapp.generated.resources.home
import zzzarchive.composeapp.generated.resources.ic_article_scroll
import zzzarchive.composeapp.generated.resources.ic_bangboo
import zzzarchive.composeapp.generated.resources.ic_cd
import zzzarchive.composeapp.generated.resources.ic_function
import zzzarchive.composeapp.generated.resources.ic_happy
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.ic_home
import zzzarchive.composeapp.generated.resources.ic_people
import zzzarchive.composeapp.generated.resources.ic_setting
import zzzarchive.composeapp.generated.resources.ic_w_engine
import zzzarchive.composeapp.generated.resources.setting
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.w_engines
import zzzarchive.composeapp.generated.resources.wiki

sealed class MainFlow(
    val route: String,
    val startScreen: Screen,
    val iconRes: DrawableResource = Res.drawable.ic_help,
    val textRes: StringResource = Res.string.unknown
) {
    data object Home : MainFlow(
        route = "home_flow",
        startScreen = Screen.Home,
        iconRes = Res.drawable.ic_home,
        textRes = Res.string.home
    )

    data object Agent : MainFlow(
        route = "agent_flow",
        startScreen = Screen.AgentsList,
        iconRes = Res.drawable.ic_people,
        textRes = Res.string.agents
    )

    data object WEngine : MainFlow(
        route = "wEngine_flow",
        startScreen = Screen.WEnginesList,
        iconRes = Res.drawable.ic_w_engine,
        textRes = Res.string.w_engines
    )

    data object Drive : MainFlow(
        route = "drive_flow",
        startScreen = Screen.DrivesList,
        iconRes = Res.drawable.ic_cd,
        textRes = Res.string.drives
    )

    data object Bangboo : MainFlow(
        route = "bangboo_flow",
        startScreen = Screen.BangbooList,
        iconRes = Res.drawable.ic_bangboo,
        textRes = Res.string.bangboo
    )

    data object Setting : MainFlow(
        route = "setting_flow",
        startScreen = Screen.Setting,
        iconRes = Res.drawable.ic_setting,
        textRes = Res.string.setting
    )

    data object Feedback : MainFlow(
        route = "feedback_flow",
        startScreen = Screen.Feedback,
        iconRes = Res.drawable.ic_happy,
        textRes = Res.string.feedback
    )

    data object Wiki : MainFlow(
        route = "wiki_flow",
        startScreen = Screen.Wiki,
        iconRes = Res.drawable.ic_article_scroll,
        textRes = Res.string.wiki
    )

    data object Function : MainFlow(
        route = "function_flow",
        startScreen = Screen.Function,
        iconRes = Res.drawable.ic_function,
        textRes = Res.string.function
    )
}

val ALL_MAIN_FLOW = listOf(
    MainFlow.Home,
    MainFlow.Agent,
    MainFlow.WEngine,
    MainFlow.Drive,
    MainFlow.Bangboo,
    MainFlow.Setting,
    MainFlow.Feedback,
    MainFlow.Wiki,
    MainFlow.Function
)

val NAV_RAIL_MAIN_FLOW = listOf(
    MainFlow.Home,
    MainFlow.Agent,
    MainFlow.WEngine,
    MainFlow.Drive,
    MainFlow.Bangboo,
    MainFlow.Setting,
    MainFlow.Feedback
)

val NAV_BOTTOM_MAIN_FLOW = listOf(
    MainFlow.Home,
    MainFlow.Wiki,
    MainFlow.Function,
    MainFlow.Setting,
)