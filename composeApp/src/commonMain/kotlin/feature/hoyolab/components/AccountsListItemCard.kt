/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzOutlineButton
import ui.components.cards.ContentCard
import ui.components.dialogs.DoubleActionDialog
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.default
import zzzarchive.composeapp.generated.resources.delete
import zzzarchive.composeapp.generated.resources.disconnect
import zzzarchive.composeapp.generated.resources.ic_delete
import zzzarchive.composeapp.generated.resources.no
import zzzarchive.composeapp.generated.resources.remove_account_hint
import zzzarchive.composeapp.generated.resources.set_as_default
import zzzarchive.composeapp.generated.resources.update_at

@Composable
fun AccountsListItemCard(
    uid: String,
    regionName: String,
    updateAt: String,
    isDefault: Boolean,
    setAsDefault: () -> Unit,
    delete: () -> Unit
) {
    var openDeleteDialog by remember { mutableStateOf(false) }
    ContentCard {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UID $uid",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelLarge
                )
                Text(
                    text = regionName,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
                Spacer(Modifier.weight(1f))
                if (isDefault) {
                    Text(
                        modifier = Modifier.clip(AppTheme.shape.round)
                            .background(AppTheme.colors.primaryContainer).padding(
                                horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                            ),
                        text = stringResource(Res.string.default),
                        color = AppTheme.colors.onPrimaryContainer,
                        style = AppTheme.typography.labelSmall
                    )
                }
            }

            Text(
                text = stringResource(Res.string.update_at, updateAt),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelSmall
            )
        }
        Spacer(Modifier.size(AppTheme.spacing.s400))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ZzzIconButton(
                iconRes = Res.drawable.ic_delete,
                contentDescriptionRes = Res.string.delete,
            ) {
                openDeleteDialog = true
            }
            ZzzOutlineButton(
                text = stringResource(Res.string.set_as_default), enabled = !isDefault
            ) {
                setAsDefault()
            }
        }
    }
    when {
        openDeleteDialog -> {
            DoubleActionDialog(
                text = stringResource(Res.string.remove_account_hint),
                primaryActionText = stringResource(Res.string.disconnect),
                secondActionText = stringResource(Res.string.no),
                onPrimaryAction = {
                    delete()
                    openDeleteDialog = false
                },
                onSecondAction = {
                    openDeleteDialog = false
                },
                onDismiss = {
                    openDeleteDialog = false
                }
            )
        }
    }
}