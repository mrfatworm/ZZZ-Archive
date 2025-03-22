/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.size.Size
import feature.hoyolab.model.my_agent_detail.MyAgentDetailListItem
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.OutlinedText
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_add
import zzzarchive.composeapp.generated.resources.ic_check
import zzzarchive.composeapp.generated.resources.ic_minus
import zzzarchive.composeapp.generated.resources.zoom_in
import zzzarchive.composeapp.generated.resources.zoom_out

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAgentImageCard(
    modifier: Modifier = Modifier, uiState: MyAgentDetailState, onApply: () -> Unit
) {
    val agentDetail = uiState.agentDetail
    ContentCard(hasDefaultPadding = false) {
        Box(modifier = modifier) {
            var scale by remember { mutableStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val state = rememberTransformableState { zoomChange, offsetChange, _ ->
                scale *= zoomChange
                offset += offsetChange
            }
            LaunchedEffect(uiState.isCustomImage) {
                if (!uiState.isCustomImage) {
                    scale = 1f
                    offset = Offset.Zero
                }
            }
            if (uiState.hasBlurBackground) {
                AsyncImage(
                    modifier = Modifier.matchParentSize().blur(8.dp),
                    model = uiState.customImgUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    filterQuality = FilterQuality.None
                )
            }
            AsyncImage(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth().graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x * scale,
                    translationY = offset.y * scale,
                ).transformable(state = state, enabled = uiState.adjustMode),
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(if (uiState.isCustomImage) uiState.customImgUrl else agentDetail.imageUrl)
                    .diskCachePolicy(CachePolicy.DISABLED).size(Size.ORIGINAL).build(),
                contentDescription = null
            )
            AgentInfo(agentDetail)
            if (uiState.adjustMode) {
                ImagePositionController(
                    modifier = Modifier.align(Alignment.TopEnd).padding(AppTheme.spacing.s400),
                    onZoomIn = { scale *= 1.1f },
                    onZoomOut = { scale *= 0.9f },
                    onApply = onApply
                )
            }
            if (uiState.customImgAuthor.isNotEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(AppTheme.spacing.s350)
                        .clip(AppTheme.shape.r300).background(AppTheme.colors.onSurfaceVariant)
                        .padding(
                            horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                        ),
                    text = uiState.customImgAuthor,
                    color = AppTheme.colors.surfaceContainer,
                    style = AppTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun AgentInfo(agentDetail: MyAgentDetailListItem) {
    Column(
        modifier = Modifier.padding(AppTheme.spacing.s400),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        OutlinedText(
            text = agentDetail.name,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.headlineMedium,
            borderColor = AppTheme.colors.surfaceLow,
            borderDrawStyle = Stroke(width = 8f, join = StrokeJoin.Round)
        )
        OutlinedText(
            text = "Lv. ${agentDetail.level}",
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.labelLarge,
            borderColor = AppTheme.colors.surfaceLow
        )
        Text(
            modifier = Modifier.clip(AppTheme.shape.r300)
                .background(AppTheme.colors.onSurfaceVariant).padding(
                    horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                ),
            text = "M${agentDetail.rank}",
            color = AppTheme.colors.surfaceContainer,
            style = AppTheme.typography.labelMedium
        )
    }
}

@Composable
private fun ImagePositionController(
    modifier: Modifier = Modifier, onZoomIn: () -> Unit, onZoomOut: () -> Unit, onApply: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
        OnImageIconButton(
            iconRes = Res.drawable.ic_minus, contentDescriptionRes = Res.string.zoom_out
        ) {
            onZoomOut()
        }
        OnImageIconButton(
            iconRes = Res.drawable.ic_add, contentDescriptionRes = Res.string.zoom_in
        ) {
            onZoomIn()
        }
        Spacer(Modifier.size(AppTheme.spacing.s300))
        OnImageIconButton(
            iconRes = Res.drawable.ic_check,
            contentDescriptionRes = Res.string.zoom_out,
            tint = AppTheme.colors.primary
        ) {
            onApply()
        }
    }
}


@Composable
private fun OnImageIconButton(
    iconRes: DrawableResource,
    contentDescriptionRes: StringResource,
    tint: Color = AppTheme.colors.onHoveredMask,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.size(AppTheme.size.iconButtonSmall),
        colors = IconButtonDefaults.iconButtonColors().copy(
            containerColor = AppTheme.colors.hoveredMask, contentColor = tint
        ),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.size.icon),
            imageVector = vectorResource(iconRes),
            contentDescription = stringResource(contentDescriptionRes)
        )
    }
}