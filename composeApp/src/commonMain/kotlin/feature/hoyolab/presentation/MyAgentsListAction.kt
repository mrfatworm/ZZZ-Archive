/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

sealed class MyAgentsListAction {
    data class ClickAgent(val agentId: Int) : MyAgentsListAction()
    data object ClickBack : MyAgentsListAction()
}