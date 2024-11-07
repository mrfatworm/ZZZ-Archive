/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.model

import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.bug
import zzzarchive.composeapp.generated.resources.incorrect_content
import zzzarchive.composeapp.generated.resources.other
import zzzarchive.composeapp.generated.resources.please_select
import zzzarchive.composeapp.generated.resources.suggestion


data class FeedbackState(
    val selectedIssueType: StringResource = Res.string.please_select,
    val issueTypes: List<StringResource> = feedbackIssueTypes,
    val appVersion: String = "",
    val deviceName: String = "",
    val operatingSystem: String = "",
)

val feedbackIssueTypes = listOf(
    Res.string.bug,
    Res.string.incorrect_content,
    Res.string.suggestion,
    Res.string.other
)




