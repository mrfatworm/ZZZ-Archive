/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.theme.AppTheme
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


@Composable
fun RarityItem(
    modifier: Modifier = Modifier,
    name: String,
    imgUrl: String? = null,
    rarity: ZzzRarity? = null,
    attribute: AgentAttribute? = null,
    specialty: AgentSpecialty? = null,
    placeHolder: @Composable () -> Unit = { ImageNotFound() },
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()

    Column(
        modifier = modifier.width(AppTheme.size.s100)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s250)
    ) {
        Box(
            modifier = Modifier.aspectRatio(1f).fillMaxSize().background(
                AppTheme.colors.imageBackground
            ).border(
                AppTheme.size.borderWidth, AppTheme.colors.imageBorder, shape = AppTheme.shape.r300
            ).clip(AppTheme.shape.r300)
        ) {
            SubcomposeAsyncImage(modifier = Modifier.fillMaxSize(),
                model = imgUrl,
                contentDescription = name,
                error = {
                    placeHolder()
                })

            if (attribute != null) {
                AttributeTag(Modifier.align(Alignment.TopEnd), attribute.textRes, attribute.iconRes)
            } else if (specialty != null) {
                AttributeTag(Modifier.align(Alignment.TopEnd), specialty.textRes, specialty.iconRes)
            }

            rarity?.let {
                RarityIndicator(
                    Modifier.align(Alignment.BottomStart), rarity, isHovered.value
                )
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
    }
}

@Composable
private fun AttributeTag(modifier: Modifier, textRes: StringResource, iconRes: DrawableResource) {
    Icon(
        modifier = modifier.background(
            AppTheme.colors.imageTagContainer,
            RoundedCornerShape(bottomStart = AppTheme.spacing.s300)
        ).padding(AppTheme.spacing.s200).size(AppTheme.size.icon),
        imageVector = vectorResource(iconRes),
        contentDescription = stringResource(textRes),
        tint = AppTheme.colors.imageOnTagContainer
    )
}

@Composable
private fun RarityIndicator(modifier: Modifier, rarity: ZzzRarity, isFocus: Boolean = false) {
    val animatedHeight by animateDpAsState(targetValue = if (isFocus) AppTheme.spacing.s300 else 0.dp)

    Spacer(
        modifier.fillMaxWidth().height(animatedHeight).background(rarity.getColor(AppTheme.colors))
    )
}
