/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

sealed class MyAgentDetailAction {
    data object ClickBack : MyAgentDetailAction()
    data class ConfirmEditImage(
        val showUid: Boolean,
        val isCustom: Boolean,
        val imageUrl: String,
        val author: String,
        val hasBlurBackground: Boolean
    ) : MyAgentDetailAction()

    data object AdjustImageDone : MyAgentDetailAction()
}