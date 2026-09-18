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

    data class SelectSkin(val skinId: Int) : MyAgentDetailAction()

    data class SetShowUid(val showUid: Boolean) : MyAgentDetailAction()

    data class SetShareDriveDetails(val showDriveDetails: Boolean) : MyAgentDetailAction()

    data class SetShareDarkTheme(val isDarkTheme: Boolean) : MyAgentDetailAction()

    /** [png] is the rendered share card, captured by the dialog. */
    data class ShareCard(val png: ByteArray) : MyAgentDetailAction()

    data class SaveCard(val png: ByteArray) : MyAgentDetailAction()

    data object DismissShareMessage : MyAgentDetailAction()
}
