/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.agent.model.Faction
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme

@Composable
fun FactionItem(faction: Faction, isSelected: Boolean = false, onFactionClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()

    BoxWithConstraints(modifier = Modifier.aspectRatio(1.8f).fillMaxWidth()
        .clip(RoundedCornerShape(16.dp)).border(
            AppTheme.dimens.borderWidth,
            if (isSelected) AppTheme.colors.primary else Color.Transparent,
            RoundedCornerShape(16.dp)
        ).pointerHoverIcon(PointerIcon.Hand).clickable(
            interactionSource = interactionSource, indication = null
        ) {
            onFactionClick()
        }) {
        AsyncImage(
            modifier = Modifier.fillMaxSize()
                .blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
            model = faction.getFactionThumbnailUrl(),
            contentDescription = stringResource(faction.getFactionNameRes()),
            contentScale = ContentScale.Crop,
            alpha = 0.7f
        )
        AsyncImage(
            modifier = Modifier.align(Alignment.Center).size(maxWidth / 3)
                .blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
            model = faction.getFactionIconUrl(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(
            visible = isPressed.value || isHovered.value, enter = fadeIn(), exit = fadeOut()
        ) {
            FactionInfo(
                Modifier.align(Alignment.BottomCenter), stringResource(faction.getFactionNameRes())
            )
        }
    }
}


@Composable
private fun FactionInfo(modifier: Modifier, factionName: String) {
    Column(
        modifier.fillMaxSize().background(AppTheme.colors.hoveredMask).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = factionName,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.onHoveredMask,
            style = AppTheme.typography.headlineSmall
        )
    }
}