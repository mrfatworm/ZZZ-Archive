/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.cache.CachePolicy
import com.github.panpf.sketch.request.ComposableImageRequest
import com.github.panpf.sketch.util.Size
import feature.hoyolab.model.agent.MyAgentDetail
import feature.hoyolab.model.agent.MyAgentDetailState
import feature.hoyolab.model.agent.MyAgentSkillAwaken
import feature.hoyolab.model.agent.MyAgentSkin
import feature.hoyolab.presentation.MyAgentDetailAction
import org.jetbrains.compose.resources.stringResource
import ui.components.OutlinedText
import ui.components.buttons.OnImageIconButton
import ui.components.buttons.ZzzIconButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_add
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_check
import zzzarchive.composeapp.generated.resources.ic_edit
import zzzarchive.composeapp.generated.resources.ic_minus
import zzzarchive.composeapp.generated.resources.outfit
import zzzarchive.composeapp.generated.resources.potential_awakening
import zzzarchive.composeapp.generated.resources.zoom_in
import zzzarchive.composeapp.generated.resources.zoom_out

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAgentImageCard(
    modifier: Modifier = Modifier,
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit
) {
    val agentDetail = uiState.agentDetail
    val selectedSkin = agentDetail.skins.firstOrNull { it.id == uiState.selectedSkinId }
    val selectedSkinImageUrl = selectedSkin?.imageUrl?.ifEmpty { agentDetail.imageUrl } ?: agentDetail.imageUrl
    ContentCard(modifier = modifier, hasDefaultPadding = false) {
        Box(modifier = Modifier.fillMaxSize()) {
            var scale by remember { mutableStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val state =
                rememberTransformableState { zoomChange, offsetChange, _ ->
                    scale *= zoomChange
                    offset += offsetChange
                }
            LaunchedEffect(uiState.isCustomImage) {
                if (!uiState.isCustomImage) {
                    scale = 1f
                    offset = Offset.Zero
                }
            }
            if (uiState.hasBlurBackground && uiState.isCustomImage) {
                AsyncImage(
                    modifier = Modifier.matchParentSize().blur(8.dp),
                    uri = uiState.customImgUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    filterQuality = FilterQuality.None
                )
            }

            // The official vertical painting is landscape (684x356) while the card is taller than
            // that, so Fit would letterbox it. Crop makes it bleed to the card edges; a custom image
            // keeps Fit, because its own blurred copy is what fills the gaps behind it.
            AsyncImage(
                modifier =
                    Modifier
                        .matchParentSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x * scale,
                            translationY = offset.y * scale
                        ).transformable(state = state, enabled = uiState.adjustMode),
                request =
                    ComposableImageRequest(
                        if (uiState.isCustomImage) uiState.customImgUrl else selectedSkinImageUrl
                    ) {
                        // A custom image is user supplied and its content can change behind the
                        // same URL, so it is never cached. The official painting and the outfits
                        // are immutable, and the outfit picker re-requests them on every tap, so
                        // caching them is what keeps switching outfits off the network.
                        if (uiState.isCustomImage) {
                            downloadCachePolicy(CachePolicy.DISABLED)
                            resultCachePolicy(CachePolicy.DISABLED)
                        }
                        size(Size.Origin)
                    },
                contentScale = if (uiState.isCustomImage) ContentScale.Fit else ContentScale.Crop,
                contentDescription = null
            )

            AgentInfo(
                agentDetail = agentDetail,
                onBackClick = { onAction(MyAgentDetailAction.ClickBack) }
            )

            if (uiState.customImgAuthor.isNotEmpty()) {
                Text(
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(AppTheme.spacing.s350)
                            .clip(AppTheme.shape.r300)
                            .background(AppTheme.colors.onSurfaceVariant)
                            .padding(
                                horizontal = AppTheme.spacing.s300,
                                vertical = AppTheme.spacing.s200
                            ),
                    text = uiState.customImgAuthor,
                    color = AppTheme.colors.surfaceContainer,
                    style = AppTheme.typography.labelMedium
                )
            }

            if (uiState.showUid) {
                OutlinedText(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(AppTheme.spacing.s400),
                    text = uiState.uid,
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelLarge,
                    borderColor = AppTheme.colors.surfaceLow
                )
            }

            if (agentDetail.skins.size > 1 && !uiState.isCustomImage && !uiState.adjustMode) {
                SkinPicker(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(AppTheme.spacing.s400),
                    skins = agentDetail.skins,
                    selectedSkinId = uiState.selectedSkinId,
                    onSkinClick = { onAction(MyAgentDetailAction.SelectSkin(it)) }
                )
            }

            val openEditDialog = remember { mutableStateOf(false) }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(AppTheme.spacing.s400),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (uiState.adjustMode) {
                    ImagePositionController(
                        onZoomIn = { scale *= 1.1f },
                        onZoomOut = { scale *= 0.9f },
                        onApply = {
                            onAction(MyAgentDetailAction.AdjustImageDone)
                        }
                    )
                }
                ZzzIconButton(iconRes = Res.drawable.ic_edit) {
                    openEditDialog.value = true
                }
            }

            when {
                openEditDialog.value -> {
                    MyAgentEditDialog(uiState, onAction) {
                        openEditDialog.value = false
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun AgentInfo(
    agentDetail: MyAgentDetail,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(AppTheme.spacing.s400),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzIconButton(
                modifier = Modifier,
                iconRes = Res.drawable.ic_arrow_back,
                contentDescriptionRes = Res.string.back,
                onClick = onBackClick
            )
        }

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

        val openMindscapeDialog = remember { mutableStateOf(false) }
        val openAwakenDialog = remember { mutableStateOf(false) }
        val mindscapes = agentDetail.mindscapes
        val awaken = agentDetail.skillAwaken

        InfoBadge(
            text = "M${agentDetail.rank}",
            onClick = if (mindscapes.isEmpty()) {
                null
            } else {
                { openMindscapeDialog.value = true }
            }
        )

        if (awaken is MyAgentSkillAwaken.Awaken) {
            InfoBadge(
                text = "${stringResource(Res.string.potential_awakening)} ${awaken.level}/${awaken.maxLevel}",
                onClick = { openAwakenDialog.value = true }
            )
        }

        if (openMindscapeDialog.value) {
            MyAgentMindscapeDialog(mindscapes = mindscapes) { openMindscapeDialog.value = false }
        }
        if (openAwakenDialog.value && awaken is MyAgentSkillAwaken.Awaken) {
            MyAgentAwakenDialog(awaken = awaken) { openAwakenDialog.value = false }
        }
    }
}

@Composable
private fun InfoBadge(
    text: String,
    onClick: (() -> Unit)?
) {
    Text(
        modifier =
            Modifier
                .clip(AppTheme.shape.r300)
                .background(AppTheme.colors.onSurfaceVariant)
                .then(
                    if (onClick == null) {
                        Modifier
                    } else {
                        Modifier.pointerHoverIcon(PointerIcon.Hand).clickable { onClick() }
                    }
                ).padding(
                    horizontal = AppTheme.spacing.s300,
                    vertical = AppTheme.spacing.s200
                ),
        text = text,
        color = AppTheme.colors.surfaceContainer,
        style = AppTheme.typography.labelMedium
    )
}

@Composable
private fun SkinPicker(
    modifier: Modifier = Modifier,
    skins: List<MyAgentSkin>,
    selectedSkinId: Int,
    onSkinClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        for (skin in skins) {
            val isSelected = skin.id == selectedSkinId
            AsyncImage(
                modifier = Modifier
                    .size(AppTheme.size.s48)
                    .clip(AppTheme.shape.r300)
                    .background(AppTheme.colors.surfaceContainer)
                    .border(
                        width = if (isSelected) 2.dp else 0.dp,
                        color = if (isSelected) AppTheme.colors.primary else AppTheme.colors.surfaceContainer,
                        shape = AppTheme.shape.r300
                    ).clickable { onSkinClick(skin.id) },
                uri = skin.squareImageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "${stringResource(Res.string.outfit)}: ${skin.name}"
            )
        }
    }
}

@Composable
private fun ImagePositionController(
    modifier: Modifier = Modifier,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onApply: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
        OnImageIconButton(
            iconRes = Res.drawable.ic_minus,
            contentDescriptionRes = Res.string.zoom_out
        ) {
            onZoomOut()
        }
        OnImageIconButton(
            iconRes = Res.drawable.ic_add,
            contentDescriptionRes = Res.string.zoom_in
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
