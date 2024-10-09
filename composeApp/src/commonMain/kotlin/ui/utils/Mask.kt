/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.ColorScheme


// Draw an Mask on the start and end of list depend on list status
fun Modifier.drawRowListMask(
    colorScheme: ColorScheme,
    width: Dp = 36.dp,
    startEnable: Boolean = false,
    endEnable: Boolean = true
) = this.drawWithContent {
    drawContent()
    if (startEnable) {
        drawRect(
            brush = Brush.horizontalGradient(
                listOf(
                    colorScheme.surfaceContainer,
                    colorScheme.surfaceContainer.copy(alpha = 0.0f),
                ), startX = 0f, endX = width.toPx()
            )
        )
    }
    if (endEnable) {
        drawRect(
            brush = Brush.horizontalGradient(
                listOf(
                    colorScheme.surfaceContainer.copy(alpha = 0.0f), colorScheme.surfaceContainer
                ), startX = size.width - width.toPx(), endX = size.width
            )
        )
    }
}

// Draw an Mask on the top and bottom of list depend on list status
fun Modifier.drawColumnListMask(
    colorScheme: ColorScheme,
    height: Dp = 36.dp,
    topEnable: Boolean = false,
    bottomEnable: Boolean = true
) = this.drawWithContent {
    drawContent()
    if (topEnable) {
        drawRect(
            brush = Brush.verticalGradient(
                listOf(
                    colorScheme.surfaceContainer,
                    colorScheme.surfaceContainer.copy(alpha = 0.0f),
                ), startY = 0f, endY = height.toPx()
            )
        )
    }
    if (bottomEnable) {
        drawRect(
            brush = Brush.verticalGradient(
                listOf(
                    colorScheme.surfaceContainer.copy(alpha = 0.0f), colorScheme.surfaceContainer
                ), startY = size.height - height.toPx(), endY = size.height
            )
        )
    }
}