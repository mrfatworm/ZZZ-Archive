/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import ui.components.ImageNotFound
import ui.theme.AppTheme
import utils.ZzzRarity


@Composable
fun RarityMiniItem(
    modifier: Modifier = Modifier,
    text: String? = null,
    imgUrl: String? = null,
    rarity: ZzzRarity? = null,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier.width(AppTheme.size.s64)
            .pointerHoverIcon(if (onClick != null) PointerIcon.Hand else PointerIcon.Default)
            .clickable(interactionSource = interactionSource, indication = null) {
                if (onClick != null) {
                    onClick()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1f).clip(AppTheme.shape.r300).background(
                rarity?.getColor(AppTheme.colors) ?: Color.Transparent
            ).border(
                AppTheme.size.borderWidth, AppTheme.colors.imageBorder, shape = AppTheme.shape.r300
            )

        ) {
            Box(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(if (rarity == null) 1f else 0.86f)
                    .background(AppTheme.colors.imageBackground)

            ) {
                if (imgUrl == null) {
                    ImageNotFound()
                } else {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(), model = imgUrl, contentDescription = null
                    )
                }
            }

        }
        text?.let {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurfaceVariant,
                maxLines = 1
            )
        }

    }
}
