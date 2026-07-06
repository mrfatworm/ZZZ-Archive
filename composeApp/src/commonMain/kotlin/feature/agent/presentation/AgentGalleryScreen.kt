/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.SubcomposeAsyncImage
import com.github.panpf.sketch.request.ComposableImageRequest
import com.github.panpf.sketch.util.Size
import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.coroutines.launch
import ui.components.ImageNotFound
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import ui.utils.verticalSafePadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentGalleryScreen(
    agentId: Int,
    portraitCount: Int,
    onBackClick: () -> Unit
) {
    val path = ZzzConfig.ASSET_PATH
    val extraPortraitImages = (1 until portraitCount).map { i ->
        "https://raw.githubusercontent.com/$path/Agent/Portrait/$agentId-$i.webp"
    }
    val imageUrls = listOf(
        "https://raw.githubusercontent.com/$path/Agent/Portrait/$agentId.webp"
    ) + extraPortraitImages + listOf(
        "https://raw.githubusercontent.com/$path/Agent/Mindscape/Partial/$agentId.webp",
        "https://raw.githubusercontent.com/$path/Agent/Mindscape/Full/$agentId.webp",
        "https://raw.githubusercontent.com/$path/W-Engine/Match-Agent/$agentId.webp"
    )
    var selectedImageUrl by remember { mutableStateOf(imageUrls.first()) }
    val contentType = AppTheme.contentType

    Scaffold(
        containerColor = AppTheme.colors.surface,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
                        ZzzIconButton(
                            modifier = Modifier.padding(start = AppTheme.spacing.s400),
                            iconRes = Res.drawable.ic_arrow_back,
                            contentDescriptionRes = Res.string.back,
                            onClick = onBackClick
                        )
                    }
                }
            )
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(verticalSafePadding())
        ) {
            if (contentType == ContentType.Single) {
                AgentGalleryScreenSingle(
                    imageUrls = imageUrls,
                    selectedImageUrl = selectedImageUrl,
                    onImageSelected = { selectedImageUrl = it }
                )
            } else {
                AgentGalleryScreenDual(
                    imageUrls = imageUrls,
                    selectedImageUrl = selectedImageUrl,
                    onImageSelected = { selectedImageUrl = it }
                )
            }
        }
    }
}

@Composable
private fun AgentGalleryScreenSingle(
    imageUrls: List<String>,
    selectedImageUrl: String,
    onImageSelected: (String) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val pagerHeight = maxHeight / 5
        val pagerState = rememberPagerState(pageCount = { imageUrls.size })
        val scope = rememberCoroutineScope()

        ZoomableImage(
            url = selectedImageUrl,
            modifier = Modifier.fillMaxSize()
        )

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(pagerHeight * (16f / 9f)),
            contentPadding = PaddingValues(horizontal = AppTheme.spacing.s500),
            pageSpacing = AppTheme.spacing.s350,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(pagerHeight)
        ) { page ->
            GalleryThumbnail(
                url = imageUrls[page],
                isSelected = imageUrls[page] == selectedImageUrl,
                onClick = {
                    onImageSelected(imageUrls[page])
                    scope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(16f / 9f)
            )
        }
    }
}

@Composable
private fun AgentGalleryScreenDual(
    imageUrls: List<String>,
    selectedImageUrl: String,
    onImageSelected: (String) -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(3.5f)
                .fillMaxHeight()
        ) {
            ZoomableImage(
                url = selectedImageUrl,
                modifier = Modifier.fillMaxSize()
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            items(imageUrls) { url ->
                GalleryThumbnail(
                    url = url,
                    isSelected = url == selectedImageUrl,
                    onClick = { onImageSelected(url) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
            }
        }
    }
}

@Composable
private fun GalleryThumbnail(
    url: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) AppTheme.colors.primary else AppTheme.colors.onSurfaceVariant
    val borderWidth = if (isSelected) AppTheme.size.largeBorder else AppTheme.size.border

    Box(
        modifier = modifier
            .clip(AppTheme.shape.r400)
            .background(AppTheme.colors.surfaceContainer.copy(alpha = 0.7f))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = AppTheme.shape.r400
            )
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            request = ComposableImageRequest(url) {
                size(Size.Origin)
            },
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            error = {
                ImageNotFound()
            }
        )
    }
}

@Composable
private fun ZoomableImage(
    url: String,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    // Reset zoom when url changes
    LaunchedEffect(url) {
        scale = 1f
        offset = Offset.Zero
    }

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale = (scale * zoomChange).coerceIn(0.7f, 5f)
        offset += offsetChange
    }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        // Mouse scroll event (desktop support if needed, or just extra)
                        event.changes.forEach { pointerInputChange ->
                            val scrollDelta = pointerInputChange.scrollDelta
                            if (scrollDelta != Offset.Zero) {
                                val zoomChange = 1 - scrollDelta.y * 0.08f
                                scale = (scale * zoomChange).coerceIn(0.7f, 5f)
                            }
                        }
                    }
                }
            }
            .transformable(state = state)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offset.x,
                translationY = offset.y
            )
    ) {
        AsyncImage(
            request = ComposableImageRequest(url) {
                size(Size.Origin)
            },
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
