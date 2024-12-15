/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.presentation

sealed interface HomeAction {
    data object ClickAgentsOverview : HomeAction
    data object ClickWEnginesOverview : HomeAction
    data object ClickBangbooOverview : HomeAction
    data object ClickDrivesOverview : HomeAction
    data class ClickAgent(val id: Int) : HomeAction
    data class ClickWEngine(val id: Int) : HomeAction
    data class ClickBangboo(val id: Int) : HomeAction
    data class ChangePixivTag(val tag: String) : HomeAction
    data class NavigateTo(val route: String) : HomeAction
    data class DismissBanner(val id: Int) : HomeAction
    data object Sign : HomeAction
}