/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

import ui.navigation.Screen

sealed interface HomeAction {
    data class ChangePixivTag(val tag: String) : HomeAction

    data class NavigateTo(val route: Screen) : HomeAction

    data class DismissBanner(val id: Int) : HomeAction

    data object Sign : HomeAction
}
