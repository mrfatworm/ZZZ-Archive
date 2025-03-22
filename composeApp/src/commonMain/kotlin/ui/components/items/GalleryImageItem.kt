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
import coil3.compose.LocalPlatformContext
import ui.theme.AppTheme
import utils.imageLoaderMemoryCache


@Composable
fun GalleryImageItem(url: String, onClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier.clickable { onClick() }.height(AppTheme.size.s144)
            .background(AppTheme.colors.surface, AppTheme.shape.r400),
        imageLoader = imageLoaderMemoryCache(LocalPlatformContext.current),
        model = url,
        contentDescription = null
    )
}