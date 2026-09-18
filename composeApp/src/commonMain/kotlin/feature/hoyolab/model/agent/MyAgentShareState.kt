/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.model.agent

/** What the share card renders; the UID toggle is [MyAgentDetailState.showUid], shared with the screen. */
data class ShareCardOptions(
    /** Off: the six disc tiles go, only the set bonuses stay. */
    val showDriveDetails: Boolean = true,
    val isDarkTheme: Boolean = true
)

/** Feedback for the last share or save. A share sheet gives its own, so it sets nothing here. */
enum class ShareMessage {
    Copied,
    Saved,
    Failed
}
