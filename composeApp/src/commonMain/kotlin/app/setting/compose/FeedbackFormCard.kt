/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package app.setting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.setting.model.FeedbackState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_version
import zzzarchive.composeapp.generated.resources.bug
import zzzarchive.composeapp.generated.resources.device_name
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios
import zzzarchive.composeapp.generated.resources.incorrect_content
import zzzarchive.composeapp.generated.resources.issue_type
import zzzarchive.composeapp.generated.resources.operating_system
import zzzarchive.composeapp.generated.resources.other
import zzzarchive.composeapp.generated.resources.please_select
import zzzarchive.composeapp.generated.resources.suggestion

@Composable
fun FeedbackFormCard(feedbackState: FeedbackState) {
    IssueTypeItem()
    SettingItemText(
        title = stringResource(Res.string.app_version),
        content = feedbackState.appVersion
    )
    SettingItemText(
        title = stringResource(Res.string.device_name),
        content = feedbackState.deviceName
    )
    SettingItemText(
        title = stringResource(Res.string.operating_system),
        content = feedbackState.operatingSystem
    )
}

val feedbackIssueTypes = listOf(
    Res.string.bug, Res.string.incorrect_content, Res.string.suggestion, Res.string.other
)

@Composable
private fun IssueTypeItem() {
    var selectedIssueType by remember { mutableStateOf(Res.string.please_select) }
    var showIssueTypes by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.issue_type), content = {
        Column(horizontalAlignment = Alignment.End) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(selectedIssueType),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showIssueTypes,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showIssueTypes = false }) {
                feedbackIssueTypes.forEach { issueType ->
                    DropdownMenuItem(text = {
                        Text(
                            text = stringResource(issueType),
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        selectedIssueType = issueType
                        showIssueTypes = false
                    })
                }
            }
        }
    }, onClick = {
        showIssueTypes = true
    })
}

@Composable
private fun SettingItemText(title: String, content: String) {
    SettingItem(title = title, content = {
        Text(
            text = content,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onSurface
        )
    }, onClick = {})
}