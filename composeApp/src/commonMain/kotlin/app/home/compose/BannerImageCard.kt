/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import app.home.model.BannerResponse
import coil3.compose.AsyncImage
import ui.component.ImageNotFound
import ui.theme.AppTheme


@Composable
fun BannerImageCard(banner: BannerResponse?) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()

    Box(
        modifier = Modifier.aspectRatio(1.7f).fillMaxWidth().clip(RoundedCornerShape(16.dp))
    ) {
        if (banner == null) {
            ImageNotFound()
        } else {
            val urlHandler = LocalUriHandler.current
            AsyncImage(
                modifier = Modifier.fillMaxSize().clickable(
                        interactionSource = interactionSource, indication = ripple(radius = 16.dp)
                    ) {
                        urlHandler.openUri(banner.artworkUrl)
                    },
                model = banner.getImageUrl(),
                contentDescription = banner.artworkName,
                contentScale = ContentScale.Crop
            )
            if (isPressed.value || isHovered.value) {
                ArtworkInfo(Modifier.align(Alignment.BottomCenter), banner)
            }
        }
    }
}


@Composable
private fun ArtworkInfo(modifier: Modifier, banner: BannerResponse) {
    Column(
        modifier.fillMaxWidth().background(AppTheme.colors.surface.copy(alpha = 0.9f))
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = banner.artworkName,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.titleMedium
        )
        Text(
            text = banner.authorName,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium
        )
    }
}