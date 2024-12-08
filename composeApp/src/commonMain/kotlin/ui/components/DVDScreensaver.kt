/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_bangboo

data class DvdState(
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    var velocityX: Float = 3f,
    var velocityY: Float = 3f,
    val size: Int = 24
)

@Composable
fun DVDScreensaver(modifier: Modifier, colors: List<Color>, imageSize: Int = 24) {
    BoxWithConstraints(modifier = modifier) {
        var currentColorIndex by remember { mutableStateOf(0) }
        var dvdState by remember { mutableStateOf(DvdState(size = imageSize)) }
        val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding().value
        val screenWidth = with(LocalDensity.current) { maxWidth.toPx() }
        val screenHeight = with(LocalDensity.current) { maxHeight.toPx() }

        LaunchedEffect(key1 = dvdState) {
            while (true) {
                delay(8) // ~60 FPS
                dvdState = updateDVDPosition(
                    dvdState,
                    screenWidth,
                    screenHeight,
                    statusBarHeight,
                    onBounce = {
                    currentColorIndex = (currentColorIndex + 1) % colors.size
                })
            }
        }
        Icon(
            modifier = Modifier.size(imageSize.dp).graphicsLayer {
                translationX = dvdState.offsetX
                translationY = dvdState.offsetY
            }.background(colors[currentColorIndex], CircleShape).padding(AppTheme.spacing.s300),
            imageVector = vectorResource(Res.drawable.ic_bangboo), // Replace with your logo
            contentDescription = "DVD screen saver logo",
            tint = AppTheme.colors.surface
        )
    }

}

private fun updateDVDPosition(
    dvdState: DvdState,
    screenWidth: Float,
    screenHeight: Float,
    statusBarHeight: Float,
    onBounce: () -> Unit
): DvdState {
    var newOffsetX = dvdState.offsetX + dvdState.velocityX
    var newOffsetY = dvdState.offsetY + dvdState.velocityY
    // Bounce off edges
    if (newOffsetX + statusBarHeight + dvdState.size * 2 >= screenWidth || newOffsetX <= 0) {
        dvdState.velocityX *= -1
        newOffsetX = dvdState.offsetX + dvdState.velocityX
        onBounce()
    }
    if (newOffsetY + statusBarHeight + dvdState.size * 2 >= screenHeight || newOffsetY <= 0) {
        dvdState.velocityY *= -1
        newOffsetY = dvdState.offsetY + dvdState.velocityY
        onBounce()
    }

    return dvdState.copy(offsetX = newOffsetX, offsetY = newOffsetY)
}
