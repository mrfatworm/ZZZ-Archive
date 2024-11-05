/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.component.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.ic_arrow_next_ios
import zzzarchive.composeapp.generated.resources.ic_figma
import zzzarchive.composeapp.generated.resources.ic_github
import zzzarchive.composeapp.generated.resources.open_source
import zzzarchive.composeapp.generated.resources.privacy_policy

@Composable
fun OtherInfoCard(
    onFeedbackClick: () -> Unit
) {
    ContentCard(hasDefaultPadding = false) {
        FeedbackItem(onClick = onFeedbackClick)
        PrivacyPolicyItem()
        OpenSourceItem()
    }
}

@Composable
private fun FeedbackItem(onClick: () -> Unit) {
    SettingItem(title = stringResource(Res.string.feedback), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }

    }, onClick = onClick)
}

@Composable
private fun PrivacyPolicyItem() {
    SettingItem(title = stringResource(Res.string.privacy_policy), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }

    }, onClick = { })
}

@Composable
private fun OpenSourceItem() {
    SettingItem(title = stringResource(Res.string.open_source), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = vectorResource(Res.drawable.ic_figma),
                contentDescription = "Figma",
                tint = Color.Unspecified
            )
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = vectorResource(Res.drawable.ic_github),
                contentDescription = "GitHub",
                tint = AppTheme.colors.onSurfaceContainer
            )
        }

    }, onClick = { })
}