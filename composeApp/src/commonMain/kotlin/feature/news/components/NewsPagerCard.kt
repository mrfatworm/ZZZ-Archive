/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.request.ComposableImageRequest
import com.github.panpf.sketch.util.Size
import feature.news.model.OfficialNewsListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.theme.AppTheme
import ui.utils.openUriSafe

@Composable
fun NewsPagerCard(newsList: List<OfficialNewsListItem>) {
    if (newsList.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = { newsList.size })
        val interactionSource = remember { MutableInteractionSource() }

        Box(
            Modifier
                .clip(AppTheme.shape.r400)
                .background(AppTheme.colors.surfaceContainer)
        ) {
            HorizontalPager(modifier = Modifier, state = pagerState) { currentPager ->
                NewsPagerCardItem(
                    newsState = newsList[currentPager],
                    interactionSource = interactionSource
                )
            }

            NewsIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(AppTheme.spacing.s400)
            )
        }

        LaunchedEffect(key1 = pagerState.settledPage) {
            launch {
                delay(6000)
                val target =
                    if (pagerState.currentPage == pagerState.pageCount - 1) 0 else pagerState.currentPage + 1
                pagerState.animateScrollToPage(target)
            }
        }
    }
}

@Composable
private fun NewsIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(AppTheme.colors.hoveredMask)
            .padding(horizontal = AppTheme.spacing.s350, vertical = AppTheme.spacing.s250)
    ) {
        Text(
            text = "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onHoveredMask
        )
    }
}

@Composable
private fun NewsPagerCardItem(
    newsState: OfficialNewsListItem,
    interactionSource: MutableInteractionSource
) {
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(16 / 9f).pointerHoverIcon(PointerIcon.Hand)
    ) {
        val urlHandler = LocalUriHandler.current
        AsyncImage(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        urlHandler.openUriSafe(newsState.newsUrl)
                    }.blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
            request = ComposableImageRequest(newsState.imageUrl) {
                size(Size.Origin)
            },
            contentDescription = newsState.title,
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(
            visible = isPressed.value || isHovered.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            NewsInfo(Modifier.align(Alignment.BottomCenter), newsState)
        }
    }
}

@Composable
private fun NewsInfo(
    modifier: Modifier,
    newsState: OfficialNewsListItem
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(AppTheme.colors.hoveredMask)
            .padding(AppTheme.spacing.s400),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        Text(
            text = newsState.title,
            color = AppTheme.colors.onHoveredMask,
            style = AppTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = newsState.description,
            color = AppTheme.colors.onHoveredMaskVariant,
            style = AppTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = newsState.date,
            color = AppTheme.colors.onHoveredMaskVariant,
            style = AppTheme.typography.labelMedium
        )
    }
}
