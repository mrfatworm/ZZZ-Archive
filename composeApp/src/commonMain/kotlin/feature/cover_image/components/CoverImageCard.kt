/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.cover_image.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.cover_image.data.database.CoverImageListItemEntity
import ui.components.ImageNotFound
import ui.theme.AppTheme


@Composable
fun CoverImageCard(coverImages: List<CoverImageListItemEntity>) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    val coverImage = coverImages.firstOrNull()

    Box(
        modifier = Modifier.aspectRatio(1.7f).fillMaxWidth().clip(AppTheme.shape.r400)
    ) {
        if (coverImage == null) {
            ImageNotFound()
        } else {
            val urlHandler = LocalUriHandler.current
            AsyncImage(
                modifier = Modifier.fillMaxSize().pointerHoverIcon(PointerIcon.Hand)
                    .clickable(
                        interactionSource = interactionSource, indication = null
                    ) {
                        urlHandler.openUri(coverImage.artworkUrl)
                    }.blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
                model = coverImage.imageUrl,
                contentDescription = coverImage.artworkName,
                contentScale = ContentScale.Crop
            )
            AnimatedVisibility(
                visible = isPressed.value || isHovered.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ArtworkInfo(Modifier.align(Alignment.BottomCenter), coverImage)
            }
        }
    }
}


@Composable
private fun ArtworkInfo(modifier: Modifier, coverImage: CoverImageListItemEntity) {
    Column(
        modifier.fillMaxWidth().background(AppTheme.colors.hoveredMask)
            .padding(AppTheme.spacing.s400),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        Text(
            modifier = Modifier,
            text = coverImage.artworkName, color = AppTheme.colors.onHoveredMask,
            style = AppTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = coverImage.artworkDescription,
            color = AppTheme.colors.onHoveredMask,
            style = AppTheme.typography.bodyMedium
        )
        Text(
            text = coverImage.authorName, color = AppTheme.colors.onHoveredMaskVariant,
            style = AppTheme.typography.labelMedium
        )
    }
}