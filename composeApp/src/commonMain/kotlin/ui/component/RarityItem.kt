/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import utils.AgentAttribute
import utils.ZzzRarity

private val itemShape = RoundedCornerShape(8.dp)

@Composable
fun RarityItem(
    modifier: Modifier = Modifier.size(100.dp), id: Int = 0,
    name: String,
    imgUrl: String? = null,
    rarityLevel: Int? = null,
    attribute: String? = null, onClick: (Int) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()

    Column(
        modifier = modifier.pointerHoverIcon(if (onClick != {}) PointerIcon.Hand else PointerIcon.Default)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick(id)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1f).background(
                AppTheme.colors.imageBackground
            ).border(3.dp, AppTheme.colors.imageBorder, shape = itemShape).clip(itemShape)
        ) {
            if (imgUrl == null) {
                ImageNotFound()
            } else {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(), model = imgUrl, contentDescription = name
                )
            }
            attribute?.let {
                AttributeTag(Modifier.align(Alignment.TopEnd), attribute)
            }

            rarityLevel?.let {
                RarityIndicator(
                    Modifier.align(Alignment.BottomStart),
                    rarityLevel,
                    isHovered.value || isPressed.value
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
private fun AttributeTag(modifier: Modifier, attribute: String) {
    val attributeEnum = AgentAttribute.entries.find {
        it.name.lowercase() == attribute.lowercase()
    } ?: AgentAttribute.Physical
    Icon(
        modifier = modifier.background(
            AppTheme.colors.imageTagContainer, RoundedCornerShape(bottomStart = 8.dp)
        ).padding(4.dp).size(18.dp),
        imageVector = vectorResource(attributeEnum.iconRes),
        contentDescription = stringResource(attributeEnum.textRes),
        tint = AppTheme.colors.imageOnTagContainer
    )
}

@Composable
private fun RarityIndicator(modifier: Modifier, rarityLevel: Int, isFocus: Boolean = false) {
    val animatedHeight by animateDpAsState(targetValue = if (isFocus) 8.dp else 0.dp)
    val rarityEnum = ZzzRarity.entries.find { it.level == rarityLevel } ?: ZzzRarity.One

    Spacer(modifier.fillMaxWidth().height(animatedHeight).background(rarityEnum.color))
}
