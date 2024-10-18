/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.agent.model.DriveItem
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItemMini
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.suggest_drives

@Composable
fun SuggestDrivesCard(drivesList: List<DriveItem>) {
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
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
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = drivesList, key = { it.id }) { drive ->
                RarityItemMini(
                    modifier = Modifier.animateItem(),
                    imgUrl = drive.getDriveIconUrl(),
                    text = drive.getSuitString()
                )
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
}