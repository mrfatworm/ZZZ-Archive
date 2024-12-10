/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

private val unfocusedIndicatorSize = 6.dp
private val focusedIndicatorSize = 16.dp

@Composable
fun PagerIndicator(modifier: Modifier, pageCount: Int, currentPage: Int, onClick: (Int) -> Unit) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth().pointerHoverIcon(PointerIcon.Hand),
        horizontalArrangement = Arrangement.Center
    ) {
        for (page in 0 until pageCount) {
            val color = AppTheme.colors.onSurfaceVariant
            val size = if (currentPage == page) focusedIndicatorSize else unfocusedIndicatorSize
            Box(modifier = Modifier
                .clickable { onClick(page) }
                .padding(AppTheme.spacing.s200)
                .clip(AppTheme.shape.r250)
                .background(color = color)
                .size(height = unfocusedIndicatorSize, width = size))
        }
    }
}