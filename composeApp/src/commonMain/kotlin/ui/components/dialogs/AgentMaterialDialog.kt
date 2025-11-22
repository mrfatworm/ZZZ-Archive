/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzPrimaryButton
import ui.components.items.RarityMiniItem
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.gallery
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.materials

@Composable
fun AgentMaterialDialog(
    materialUrl: String,
    weeklyMaterialUrl: String,
    skillMaterialUrls: List<String>,
    levelMaterialUrls: List<String>,
    wEngineMaterialUrls: List<String>,
    onGalleryClick: () -> Unit,
    onDismiss: () -> Unit
) {
    BasicDialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.materials),
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
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
            ) {
                RarityMiniItem(imgUrl = materialUrl)
                RarityMiniItem(imgUrl = weeklyMaterialUrl)
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
            ) {
                items(skillMaterialUrls) {
                    RarityMiniItem(imgUrl = it)
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
            ) {
                items(levelMaterialUrls) {
                    RarityMiniItem(imgUrl = it)
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
            ) {
                items(wEngineMaterialUrls) {
                    RarityMiniItem(imgUrl = it)
                }
            }
            ZzzPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.gallery),
                onClick = onGalleryClick
            )
        }
    }
}
