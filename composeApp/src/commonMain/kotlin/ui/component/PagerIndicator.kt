/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

@Composable
fun PagerIndicator(modifier: Modifier, pageCount: Int, currentPage: Int, onClick: (Int) -> Unit) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (page in 0 until pageCount) {
            val color = AppTheme.colors.onSurfaceVariant
            val size = if (currentPage == page) 16.dp else 6.dp
            Box(modifier = Modifier
                .clickable { onClick(page) }
                .padding(4.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = color)
                .size(height = 6.dp, width = size))
        }
    }
}