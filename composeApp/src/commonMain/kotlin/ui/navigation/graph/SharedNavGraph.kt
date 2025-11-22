/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import feature.agent.presentation.AgentGalleryScreen
import feature.agent.presentation.AgentsListScreen
import feature.feedback.presentation.FeedbackScreen
import feature.hoyolab.presentation.HoYoLabSyncScreen
import feature.hoyolab.presentation.MyAgentDetailScreen
import feature.hoyolab.presentation.MyAgentsListScreen
import feature.setting.presentation.SettingScreen
import ui.navigation.NavActions
import ui.navigation.Screen
import ui.navigation.slideInAnimateComposable

fun NavGraphBuilder.sharedNavGraph(navActions: NavActions) {
    composable<Screen.AgentsList> {
        AgentsListScreen(onAgentClick = { id ->
            navActions.navigationTo(Screen.AgentDetail(id))
        }, onGalleryClick = { id ->
            navActions.navigationTo(Screen.AgentGallery(id))
        }, onBackClick = {
            navActions.back()
        })
    }

    composable<Screen.AgentGallery> {
        val args = it.toRoute<Screen.AgentGallery>()
        AgentGalleryScreen(
            agentId = args.id,
            onBackClick = {
                navActions.back()
            }
        )
    }

    composable<Screen.Setting> {
        SettingScreen(onFeedbackClick = {
            navActions.navigationTo(Screen.Feedback)
        }, onHoYoLabClick = {
            navActions.navigationTo(Screen.HoYoLabSync)
        })
    }

    slideInAnimateComposable<Screen.Feedback> {
        FeedbackScreen {
            navActions.back()
        }
    }

    slideInAnimateComposable<Screen.HoYoLabSync> {
        HoYoLabSyncScreen(onBackClick = { navActions.back() }, navigateToFeedback = {
            navActions.navigationTo(Screen.Feedback)
        })
    }

    slideInAnimateComposable<Screen.MyAgentsList> {
        MyAgentsListScreen(
            onAgentClick = { id ->
                navActions.navigationTo(Screen.MyAgentDetail(id))
            },
            onBackClick = {
                navActions.back()
            }
        )
    }

    slideInAnimateComposable<Screen.MyAgentDetail> {
        MyAgentDetailScreen(onBackClick = {
            navActions.back()
        })
    }
}
