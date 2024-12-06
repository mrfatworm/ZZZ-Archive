/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ui.theme.AppTheme


@Composable
fun GalleryImageItem(url: String, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier.clickable { onClick() }.height(AppTheme.size.galleryItemSize)
            .background(AppTheme.colors.surface, RoundedCornerShape(16.dp)),
        model = url,
        contentDescription = null
    )
}