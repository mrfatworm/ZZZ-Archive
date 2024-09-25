/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation

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
import zzzarchive.composeapp.generated.resources.ic_home
import zzzarchive.composeapp.generated.resources.ic_people
import zzzarchive.composeapp.generated.resources.ic_setting
import zzzarchive.composeapp.generated.resources.ic_w_engine
import zzzarchive.composeapp.generated.resources.setting
import zzzarchive.composeapp.generated.resources.w_engines
import zzzarchive.composeapp.generated.resources.wiki

sealed interface MainFlow {
    data object Home : Screen(
        route = "home_flow", iconRes = Res.drawable.ic_home, textRes = Res.string.home
    )

    data object Agent : Screen(
        route = "agent_flow", iconRes = Res.drawable.ic_people, textRes = Res.string.agents
    )

    data object WEngine : Screen(
        route = "wEngine_flow", iconRes = Res.drawable.ic_w_engine, textRes = Res.string.w_engines
    )

    data object Drive : Screen(
        route = "drive_flow", iconRes = Res.drawable.ic_cd, textRes = Res.string.drives
    )

    data object Bangboo : Screen(
        route = "bangboo_flow", iconRes = Res.drawable.ic_bangboo, textRes = Res.string.bangboo
    )

    data object Setting : Screen(
        route = "setting_flow", iconRes = Res.drawable.ic_setting, textRes = Res.string.setting
    )

    data object Feedback : Screen(
        route = "feedback_flow", iconRes = Res.drawable.ic_happy, textRes = Res.string.feedback
    )

    data object Wiki : Screen(
        route = "wiki_flow", iconRes = Res.drawable.ic_article_scroll, textRes = Res.string.wiki
    )

    data object Function : Screen(
        route = "function_flow", iconRes = Res.drawable.ic_function, textRes = Res.string.function
    )
}

val TOP_LEVEL_DESTINATIONS_MEDIUM = listOf(
    MainFlow.Home,
    MainFlow.Agent,
    MainFlow.WEngine,
    MainFlow.Drive,
    MainFlow.Bangboo,
    MainFlow.Setting,
    MainFlow.Feedback
)

val TOP_LEVEL_DESTINATIONS_COMPACT = listOf(
    MainFlow.Home,
    MainFlow.Wiki,
    MainFlow.Function,
    MainFlow.Setting,
)