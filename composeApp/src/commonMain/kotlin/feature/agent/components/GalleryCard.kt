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
import feature.agent.model.AgentDetail
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.components.dialogs.GalleryDialog
import ui.components.items.GalleryImageItem
import ui.theme.AppTheme
import ui.utils.cardPaddingWithHeader
import ui.utils.drawRowListMask
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.gallery

@Composable
fun GalleryCard(agentDetail: AgentDetail) {
    val galleryUrlList = listOf(
        agentDetail.portraitUrl,
        agentDetail.mindscapePartialUrl,
        agentDetail.mindscapeFullUrl
    )
    val openGalleryDialog = remember { mutableStateOf(false) }
    val galleryDialogUrl = remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(
            title = stringResource(Res.string.gallery),
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
            items(items = galleryUrlList) { url ->
                GalleryImageItem(url = url) {
                    galleryDialogUrl.value = url
                    openGalleryDialog.value = true
                }
            }
        }
    }
    when {
        openGalleryDialog.value -> GalleryDialog(url = galleryDialogUrl.value, onDismiss = {
            openGalleryDialog.value = false
        })
    }
}
