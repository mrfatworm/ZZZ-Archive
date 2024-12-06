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
import coil3.compose.AsyncImage
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close

@Composable
fun GalleryDialog(url: String, onDismiss: () -> Unit) {
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
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .transformable(state = state)
                    .fillMaxSize(),
                model = url,
                contentDescription = null,
                filterQuality = FilterQuality.High
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