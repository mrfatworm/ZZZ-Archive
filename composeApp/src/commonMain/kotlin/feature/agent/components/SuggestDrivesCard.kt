/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import feature.agent.model.AgentDriveItem
import feature.drive.data.database.DrivesListItemEntity
import feature.drive.data.database.emptyDriveListItemEntity
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.components.dialogs.DriveDetailDialog
import ui.components.items.RarityMiniItem
import ui.theme.AppTheme
import ui.utils.cardPaddingWithHeader
import ui.utils.drawRowListMask
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.piece_set_short
import zzzarchive.composeapp.generated.resources.suggest_drives

@Composable
fun SuggestDrivesCard(
    agentDrivesList: List<AgentDriveItem>,
    drivesList: List<DrivesListItemEntity>
) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val openDetailDialog = remember { mutableStateOf(false) }
    val selectedDriveId = remember { mutableStateOf(0) }
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(
            title = stringResource(Res.string.suggest_drives),
            isHovered = isHovered.value && (lazyListState.canScrollForward || lazyListState.canScrollBackward),
            lazyListState = lazyListState
        )
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ),
            state = lazyListState,
            contentPadding = cardPaddingWithHeader(),
            horizontalArrangement = rowListGap()
        ) {
            items(items = agentDrivesList) { drive ->
                RarityMiniItem(
                    imgUrl = drive.getDriveIconUrl(),
                    text = stringResource(Res.string.piece_set_short, drive.getSuitString())
                ) {
                    selectedDriveId.value = drive.id
                    openDetailDialog.value = true
                }
            }
        }

        when {
            openDetailDialog.value -> {
                DriveDetailDialog(drivesListItemEntity = drivesList.find { it.id == selectedDriveId.value }
                    ?: emptyDriveListItemEntity, onDismiss = {
                    openDetailDialog.value = false
                })
            }
        }
    }
}