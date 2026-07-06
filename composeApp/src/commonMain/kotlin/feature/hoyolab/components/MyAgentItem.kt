/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.github.panpf.sketch.SubcomposeAsyncImage
import ui.components.ImageNotFound
import ui.theme.AppTheme
import utils.ZzzRarity

@Composable
fun MyAgentItem(
    name: String,
    level: Int,
    rank: Int,
    modifier: Modifier = Modifier,
    imgUrl: String? = null,
    rarity: ZzzRarity = ZzzRarity.RARITY_D,
    placeHolder: @Composable () -> Unit = { ImageNotFound() },
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Column(
        modifier =
            modifier
                .border(
                    width = AppTheme.size.border,
                    color = AppTheme.colors.imageBorder,
                    shape = AppTheme.shape.r300
                )
                .clip(AppTheme.shape.r300)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(),
                uri = imgUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                error = {
                    placeHolder()
                }
            )

            if (rank > 0) {
                CinemaCountTag(
                    modifier = Modifier.align(Alignment.TopEnd),
                    cinemaCount = rank
                )
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = isHovered || isPressed,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                RarityIndicator(
                    rarity = rarity
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.hoveredMask)
                .border(
                    width = AppTheme.size.border,
                    color = AppTheme.colors.imageBorder
                )
                .padding(vertical = AppTheme.spacing.s200),
            text = "Lv $level",
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onHoveredMaskVariant,
            maxLines = 1
        )
    }
}

@Composable
private fun CinemaCountTag(
    modifier: Modifier,
    cinemaCount: Int
) {
    Text(
        modifier =
            modifier
                .background(
                    AppTheme.colors.hoveredMask,
                    RoundedCornerShape(bottomStart = AppTheme.spacing.s300)
                ).padding(AppTheme.spacing.s200),
        text = cinemaCount.toString(),
        color = AppTheme.colors.onHoveredMask,
        style = AppTheme.typography.labelLarge
    )
}

@Composable
private fun RarityIndicator(
    modifier: Modifier = Modifier,
    rarity: ZzzRarity
) {
    Spacer(
        modifier
            .fillMaxWidth()
            .height(AppTheme.size.largeBorder)
            .background(rarity.getColor(AppTheme.colors))
    )
}
