/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.SubcomposeAsyncImage
import ui.components.ImageNotFound
import ui.theme.AppTheme
import utils.ZzzRarity


@Composable
fun MyAgentItem(
    modifier: Modifier = Modifier,
    name: String,
    level: Int,
    rank: Int,
    imgUrl: String? = null,
    rarity: ZzzRarity = ZzzRarity.RARITY_D,
    placeHolder: @Composable () -> Unit = { ImageNotFound() },
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier.width(AppTheme.size.rarityItemMediumSize)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s250)
    ) {
        Box(
            modifier = Modifier.aspectRatio(9f / 11).fillMaxSize().background(
                AppTheme.colors.imageBackground
            ).border(
                AppTheme.size.borderWidth, AppTheme.colors.imageBorder, shape = AppTheme.shape.r300
            ).clip(AppTheme.shape.r300)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imgUrl,
                contentDescription = name,
                error = {
                    placeHolder()
                })

            if (rank > 0) {
                CinemaCountTag(Modifier.align(Alignment.TopEnd), rank)
            }

            RarityIndicator(Modifier.align(Alignment.BottomStart), rarity)
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Lv $level",
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.labelLarge,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
    }
}

@Composable
private fun CinemaCountTag(modifier: Modifier, cinemaCount: Int) {
    Text(
        modifier = modifier.background(
            AppTheme.colors.imageTagContainer,
            RoundedCornerShape(bottomStart = AppTheme.spacing.s300)
        ).padding(horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200),
        text = cinemaCount.toString(),
        color = AppTheme.colors.imageOnTagContainer,
        style = AppTheme.typography.labelLarge
    )
}

@Composable
private fun RarityIndicator(modifier: Modifier, rarity: ZzzRarity) {
    Spacer(
        modifier.fillMaxWidth().height(AppTheme.spacing.s300)
            .background(rarity.getColor(AppTheme.colors))
    )
}
