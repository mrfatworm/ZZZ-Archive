/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.size.Size
import feature.agent.model.AgentListItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import utils.ZzzRarity

@Composable
fun HighlightAgentListItem(
    modifier: Modifier = Modifier,
    uiState: AgentListItem,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier =
            modifier
                .widthIn(min = AppTheme.size.s280)
                .clip(AppTheme.shape.r300)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
                .background(
                    AppTheme.colors.surfaceContainer
                ).border(
                    width = if (isHovered) AppTheme.size.largeBorder else AppTheme.size.border,
                    color = if (isHovered) uiState.rarity.getColor(AppTheme.colors) else AppTheme.colors.imageBorder,
                    shape = AppTheme.shape.r300
                )
    ) {
        RarityBackground(
            modifier = Modifier.matchParentSize(),
            rarity = uiState.rarity,
            isFocus = isHovered
        )

        // Faction Background Image
        Box(modifier = Modifier.matchParentSize()) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.38f)
                    .alpha(0.2f),
                model = uiState.faction.getFactionIconUrl(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(ratio = 1f)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .matchParentSize(),
                    model = ImageRequest.Builder(LocalPlatformContext.current)
                        .data(uiState.imageUrl)
                        .size(Size.ORIGINAL)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }

            Column(
                modifier = Modifier.weight(1f).padding(AppTheme.spacing.s300),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                horizontalAlignment = Alignment.End
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
                    Icon(
                        modifier = Modifier.size(AppTheme.size.icon),
                        imageVector = vectorResource(uiState.specialty.iconRes),
                        contentDescription = stringResource(uiState.specialty.textRes),
                        tint = AppTheme.colors.onSurfaceContainer
                    )
                    Icon(
                        modifier = Modifier.size(AppTheme.size.icon),
                        imageVector = vectorResource(uiState.attribute.iconRes),
                        contentDescription = stringResource(uiState.attribute.textRes),
                        tint = uiState.attribute.getColor(AppTheme.colors)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)) {
                    RarityMiniItem(imgUrl = uiState.weeklyMaterialUrl)
                    RarityMiniItem(imgUrl = uiState.materialUrl)
                }
            }
        }
    }
}

@Composable
fun RarityBackground(
    modifier: Modifier,
    rarity: ZzzRarity,
    isFocus: Boolean = false
) {
    val backgroundColors = listOf(rarity.getColor(AppTheme.colors), AppTheme.colors.surfaceContainer)
    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = isFocus
    ) {
        Spacer(
            modifier.fillMaxSize().background(brush = Brush.linearGradient(colors = backgroundColors))
        )
    }
}
