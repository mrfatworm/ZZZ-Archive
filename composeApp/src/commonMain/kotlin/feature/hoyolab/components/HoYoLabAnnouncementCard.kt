/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.hoyolab_sync_announcement
import zzzarchive.composeapp.generated.resources.ic_info

@Composable
fun HoYoLabAnnouncementCard() {
    Row(
        modifier = Modifier.clip(AppTheme.shape.r400).fillMaxWidth()
            .background(AppTheme.colors.primaryContainer).padding(
                horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300
            ), horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_info),
            contentDescription = null,
            tint = AppTheme.colors.onPrimaryContainer
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.hoyolab_sync_announcement),
            color = AppTheme.colors.onPrimaryContainer,
            style = AppTheme.typography.bodyMedium
        )
    }
}