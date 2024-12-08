/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import ui.theme.AppTheme


@Composable
fun GalleryImageItem(url: String, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier.clickable { onClick() }.height(AppTheme.size.galleryItemSize)
            .background(AppTheme.colors.surface, AppTheme.shape.r400),
        model = url,
        contentDescription = null
    )
}