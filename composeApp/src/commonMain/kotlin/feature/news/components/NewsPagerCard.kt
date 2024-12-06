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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.news.model.OfficialNewsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.components.PagerIndicator
import ui.theme.AppTheme


@Composable
fun NewsPagerCard(newsList: List<OfficialNewsState>) {
    val pagerState = rememberPagerState(pageCount = { newsList.size })
    val coroutineScope = rememberCoroutineScope()
    Column(
        Modifier.clip(AppTheme.shape.cardShape).background(AppTheme.colors.surfaceContainer)
            .padding(bottom = AppTheme.spacing.s400),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
    ) {
        HorizontalPager(modifier = Modifier, state = pagerState) { currentPager ->
            NewsPagerCardItem(newsList[currentPager])
        }
        PagerIndicator(modifier = Modifier,
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            })
    }

    LaunchedEffect(key1 = pagerState.settledPage) {
        launch {
            delay(8000)
            val target =
                if (pagerState.currentPage == pagerState.pageCount - 1) 0 else pagerState.currentPage + 1
            pagerState.animateScrollToPage(target)
        }
    }

}


@Composable
fun NewsPagerCardItem(newsState: OfficialNewsState) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(1.7f).pointerHoverIcon(PointerIcon.Hand)
    ) {

        val urlHandler = LocalUriHandler.current
        AsyncImage(
            modifier = Modifier.fillMaxSize().clickable(
                interactionSource = interactionSource, indication = null
            ) {
                urlHandler.openUri(newsState.newsUrl)
            }.blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
            model = newsState.imageUrl,
            contentDescription = newsState.title,
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(
            visible = isPressed.value || isHovered.value, enter = fadeIn(), exit = fadeOut()
        ) {
            NewsInfo(Modifier.align(Alignment.BottomCenter), newsState)
        }
    }
}

@Composable
private fun NewsInfo(modifier: Modifier, newsState: OfficialNewsState) {
    Column(
        modifier.fillMaxWidth().background(AppTheme.colors.hoveredMask)
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
            style = AppTheme.typography.labelMedium,
            textAlign = TextAlign.End
        )
    }
}