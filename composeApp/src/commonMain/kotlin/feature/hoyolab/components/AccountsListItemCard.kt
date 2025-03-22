/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.SubcomposeAsyncImage
import feature.hoyolab.model.SyncedAccountsListItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzOutlineButton
import ui.components.cards.ContentCard
import ui.components.dialogs.DoubleActionDialog
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.default
import zzzarchive.composeapp.generated.resources.delete
import zzzarchive.composeapp.generated.resources.ic_delete
import zzzarchive.composeapp.generated.resources.ic_refresh
import zzzarchive.composeapp.generated.resources.no
import zzzarchive.composeapp.generated.resources.remove_account_hint
import zzzarchive.composeapp.generated.resources.set_as_default
import zzzarchive.composeapp.generated.resources.sync
import zzzarchive.composeapp.generated.resources.unsync
import zzzarchive.composeapp.generated.resources.update_at
import zzzarchive.composeapp.generated.resources.user_profile_image


@Composable
fun AccountsListItemCard(
    modifier: Modifier = Modifier,
    uiState: SyncedAccountsListItem,
    isDefault: Boolean,
    syncable: Boolean = true,
    sync: () -> Unit,
    setAsDefault: () -> Unit,
    delete: () -> Unit
) {
    var openDeleteDialog by remember { mutableStateOf(false) }
    ContentCard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)) {
            BasicInfo(uiState, syncable, sync, isDefault)
            Action(uiState, isDefault, setAsDefault) {
                openDeleteDialog = true
            }
        }
    }
    when {
        openDeleteDialog -> {
            DoubleActionDialog(text = stringResource(Res.string.remove_account_hint),
                primaryActionText = stringResource(Res.string.unsync),
                secondaryActionText = stringResource(Res.string.no),
                onPrimaryAction = {
                    delete()
                    openDeleteDialog = false
                },
                onSecondaryAction = {
                    openDeleteDialog = false
                },
                onDismiss = {
                    openDeleteDialog = false
                })
        }
    }
}

@Composable
private fun BasicInfo(
    uiState: SyncedAccountsListItem, syncable: Boolean = true, sync: () -> Unit, isDefault: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(AppTheme.size.s48)
            .clip(CircleShape),
            model = uiState.profileUrl,
            contentDescription = stringResource(Res.string.user_profile_image),
            error = {
                ImageNotFound()
            })
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiState.nickname,
                    color = AppTheme.colors.onSurface,
                    style = AppTheme.typography.labelLarge
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.icon).clickable {
                        if (syncable) {
                            sync()
                        }
                    },
                    imageVector = vectorResource(Res.drawable.ic_refresh),
                    contentDescription = stringResource(Res.string.sync),
                    tint = AppTheme.colors.onSurfaceVariant.copy(alpha = if (syncable) 1f else 0.5f)
                )
                Spacer(Modifier.weight(1f))
                if (isDefault) {
                    Text(
                        modifier = Modifier.clip(CircleShape)
                            .background(AppTheme.colors.primaryContainer).padding(
                                horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                            ),
                        text = stringResource(Res.string.default),
                        color = AppTheme.colors.onPrimaryContainer,
                        style = AppTheme.typography.labelSmall
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UID ${uiState.uid}",
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelMedium
                )
                Text(
                    text = uiState.regionName,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun Action(
    uiState: SyncedAccountsListItem,
    isDefault: Boolean,
    setAsDefault: () -> Unit,
    openDeleteDialog: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.update_at, uiState.datetime),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodySmall,
            maxLines = 2
        )
        ZzzIconButton(
            iconRes = Res.drawable.ic_delete,
            contentDescriptionRes = Res.string.delete,
        ) {
            openDeleteDialog()
        }
        ZzzOutlineButton(
            text = stringResource(Res.string.set_as_default), enabled = !isDefault
        ) {
            setAsDefault()
        }
    }
}
