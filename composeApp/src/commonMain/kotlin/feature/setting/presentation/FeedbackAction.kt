/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import feature.setting.model.FeedbackIssueType

sealed interface FeedbackAction {
    data class SubmitForm(
        val issueIndex: FeedbackIssueType, val issueContent: String, val nickname: String
    ) : FeedbackAction

    data object DismissDialog : FeedbackAction
    data object ClickBack : FeedbackAction
}