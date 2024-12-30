/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.size.Size
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close

@Composable
fun GalleryDialog(url: String, onDismiss: () -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(AppTheme.colors.surface.copy(alpha = 0.7f))
        ) {
            var scale by remember { mutableStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val state = rememberTransformableState { zoomChange, offsetChange, _ ->
                scale *= zoomChange
                offset += offsetChange
            }
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x * scale,
                        translationY = offset.y * scale,
                    ).pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                // Mouse scroll event
                                event.changes.forEach { pointerInputChange ->
                                    val scrollDelta = pointerInputChange.scrollDelta
                                    if (scrollDelta != Offset.Zero) {
                                        val zoomChange = 1 - scrollDelta.y * 0.08f
                                        scale = (scale * zoomChange).coerceIn(0.5f, 5f) // 設定縮放範圍
                                    }
                                }
                            }
                        }
                    }
                    .transformable(state = state),
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(url)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .size(Size.ORIGINAL).build(),
                contentDescription = null,
                filterQuality = FilterQuality.High,
            )
        }
        ZzzIconButton(
            modifier = Modifier.padding(AppTheme.spacing.s400),
            iconRes = Res.drawable.ic_close,
            contentDescriptionRes = Res.string.close,
            onClick = onDismiss
        )
    }
}