/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data object AgentsList : Screen

    @Serializable
    data class AgentDetail(val id: Int) : Screen

    @Serializable
    data object Setting : Screen

    @Serializable
    data object Feedback : Screen

    @Serializable
    data object HoYoLabSync : Screen

    @Serializable
    data object MyAgentsList : Screen

    @Serializable
    data class MyAgentDetail(val id: Int) : Screen

    @Serializable
    data class AgentGallery(val id: Int) : Screen
}
