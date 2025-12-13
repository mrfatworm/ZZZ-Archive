/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
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
    name: String? = null,
    imgUrl: String? = null,
    rarity: ZzzRarity? = null,
    attribute: AgentAttribute? = null,
    specialty: AgentSpecialty? = null,
    isSelected: Boolean = false,
    placeHolder: @Composable () -> Unit = { ImageNotFound() },
    onHover: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    LaunchedEffect(isHovered) {
        onHover(isHovered)
    }

    Column(
        modifier =
            modifier
                .width(AppTheme.size.s100)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null) {
                    onClick()
                },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s250)
    ) {
        Box(
            modifier =
                Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
                    .clip(AppTheme.shape.r300)
                    .background(
                        AppTheme.colors.surfaceContainer
                    ).border(
                        width = if (isHovered || isSelected) AppTheme.size.largeBorder else AppTheme.size.border,
                        color = if (isHovered || isSelected) {
                            rarity?.getColor(AppTheme.colors) ?: AppTheme.colors.imageBorder
                        } else {
                            AppTheme.colors.imageBorder
                        },
                        shape = AppTheme.shape.r300
                    )
        ) {
            rarity?.let {
                RarityBackground(
                    modifier = Modifier.matchParentSize(),
                    rarity = rarity,
                    isFocus = isHovered || isSelected
                )
            }
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(imgUrl)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = name,
                error = {
                    placeHolder()
                }
            )

            if (attribute != null) {
                AttributeTag(
                    modifier = Modifier.align(Alignment.TopEnd),
                    textRes = attribute.textRes,
                    iconRes = attribute.iconRes
                )
            } else if (specialty != null) {
                AttributeTag(
                    modifier = Modifier.align(Alignment.TopEnd),
                    textRes = specialty.textRes,
                    iconRes = specialty.iconRes
                )
            }
        }
        name?.let { name ->
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
}

@Composable
private fun AttributeTag(
    textRes: StringResource,
    iconRes: DrawableResource,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier =
            modifier
                .background(
                    AppTheme.colors.imageTagContainer,
                    RoundedCornerShape(bottomStart = AppTheme.spacing.s300)
                ).padding(AppTheme.spacing.s200)
                .size(AppTheme.size.icon),
        imageVector = vectorResource(iconRes),
        contentDescription = stringResource(textRes),
        tint = AppTheme.colors.imageOnTagContainer
    )
}
