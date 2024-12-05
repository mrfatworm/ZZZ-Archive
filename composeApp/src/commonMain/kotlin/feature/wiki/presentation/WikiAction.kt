/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wiki.presentation

sealed interface WikiAction {
    data object ClickAgentsOverview : WikiAction
    data object ClickWEnginesOverview : WikiAction
    data object ClickBangbooOverview : WikiAction
    data object ClickDrivesOverview : WikiAction
    data class ClickAgent(val id: Int) : WikiAction
    data class ClickWEngine(val id: Int) : WikiAction
    data class ClickBangboo(val id: Int) : WikiAction
}