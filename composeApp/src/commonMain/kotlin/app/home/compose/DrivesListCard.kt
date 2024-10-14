/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import app.drive.model.DriveListItem
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItem
import ui.component.RowListFooterItem
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.all_drives
import zzzarchive.composeapp.generated.resources.drives


@Composable
fun DrivesListCard(
    drivesList: List<DriveListItem>, showViewAll: Boolean = false,
    onDrivesOverviewClick: () -> Unit,
    onDriveDetailClick: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(modifier = Modifier.fillMaxWidth(),
            titleRes = Res.string.drives,
            viewAllTextRes = if (showViewAll) Res.string.all_drives else null,
            isHovered = isHovered.value,
            onPreviousClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex - 3
                coroutineScope.launch {
                    if (targetIndex >= 0) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            },
            onNextClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex + 3
                coroutineScope.launch {
                    if (targetIndex < drivesList.size) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(drivesList.size - 1)
                    }
                }
            },
            onViewAllClick = onDrivesOverviewClick
        )
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ), state = lazyListState,
            contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = drivesList, key = { it.id }) { drive ->
                RarityItem(
                    modifier = Modifier.animateItem(),
                    name = drive.name,
                    imgUrl = drive.getProfileUrl(),
                    onClick = {
                        onDriveDetailClick(drive.id)
                    })
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
            item {
                RowListFooterItem(text = stringResource(Res.string.all_drives)) {
                    onDrivesOverviewClick()
                }
            }
        }
    }
}

