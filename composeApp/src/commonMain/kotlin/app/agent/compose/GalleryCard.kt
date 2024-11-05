/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.agent.model.AgentDetailResponse
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.gallery
import zzzarchive.composeapp.generated.resources.ic_close

@Composable
fun GalleryCard(agentDetail: AgentDetailResponse) {
    val galleryUrlList = listOf(
        agentDetail.getAgentPortraitImageUrl(),
        agentDetail.getAgentMindScapePartialImageUrl(),
        agentDetail.getAgentMindScapeFullImageUrl()
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
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = galleryUrlList) { url ->
                GalleryImageItem(url = url) {
                    galleryDialogUrl.value = url
                    openGalleryDialog.value = true
                }
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
    when {
        openGalleryDialog.value -> GalleryDialog(url = galleryDialogUrl.value, onDismiss = {
            openGalleryDialog.value = false
        })
    }
}

@Composable
fun GalleryImageItem(url: String, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier.clickable { onClick() }.height(120.dp)
            .background(AppTheme.colors.surface, RoundedCornerShape(16.dp)),
        model = url,
        contentDescription = null
    )
}

@Composable
fun GalleryDialog(url: String, onDismiss: () -> Unit) {
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(AppTheme.colors.surface.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                modifier = Modifier
                    // apply other transformations like rotation and zoom
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state)
                    .fillMaxSize(),
                model = url,
                contentDescription = null,
                filterQuality = FilterQuality.High
            )
        }
        ZzzIconButton(
            modifier = Modifier.padding(16.dp),
            iconRes = Res.drawable.ic_close,
            contentDescriptionRes = Res.string.close,
            onClick = onDismiss
        )
    }
}