/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.drive.model.DriveListItem
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.piece_set

@Composable
fun DriveDetailCard(
    modifier: Modifier = Modifier, driveListItem: DriveListItem, onDismiss: () -> Unit
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceContainer,
            contentColor = AppTheme.colors.onSurfaceContainer
        )
    ) {
        Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = driveListItem.name,
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleMedium
            )
            ZzzIconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                iconRes = Res.drawable.ic_close,
                contentDescriptionRes = Res.string.close
            ) {
                onDismiss()
            }
        }
        Column(
            Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.widthIn(max = 160.dp, min = 120.dp).aspectRatio(1f),
                model = driveListItem.getProfileUrl(),
                contentDescription = null
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.piece_set, 2),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.radius.contentCard))
                        .background(AppTheme.colors.surface)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = driveListItem.pieceSetTwo,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.onSurface
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.piece_set, 4),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.radius.contentCard))
                        .background(AppTheme.colors.surface)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = driveListItem.pieceSetFour,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.onSurface
                )
            }
        }
    }
}