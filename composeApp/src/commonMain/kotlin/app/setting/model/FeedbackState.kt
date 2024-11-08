/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.model

import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.crash_or_bug
import zzzarchive.composeapp.generated.resources.incorrect_content
import zzzarchive.composeapp.generated.resources.other
import zzzarchive.composeapp.generated.resources.please_select
import zzzarchive.composeapp.generated.resources.suggestion
import zzzarchive.composeapp.generated.resources.unknown_error


data class FeedbackState(
    val issueTypes: List<FeedbackIssueType> = feedbackIssueTypes,
    val language: String = "",
    val appVersion: String = "",
    val deviceName: String = "",
    val operatingSystem: String = "",
    val showSubmitSuccessDialog: Boolean = false,
    val invalidForm: Boolean = false,
    val invalidMessage: StringResource = Res.string.unknown_error
)

data class FeedbackIssueType(
    val localStringRes: StringResource,
    val chtString: String
)

val feedbackIssueTypes = listOf(
    FeedbackIssueType(Res.string.please_select, "未選擇類型"),
    FeedbackIssueType(Res.string.crash_or_bug, "閃退或 Bug"),
    FeedbackIssueType(Res.string.incorrect_content, "文本、內容錯誤"),
    FeedbackIssueType(Res.string.suggestion, "建議"),
    FeedbackIssueType(Res.string.other, "其他")
)





