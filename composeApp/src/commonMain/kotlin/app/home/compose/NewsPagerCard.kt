/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.home.model.OfficialNewsData
import app.home.model.OfficialNewsListItem
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.component.ImageNotFound
import ui.component.PagerIndicator
import ui.theme.AppTheme


@Composable
fun NewsPagerCard(newsData: OfficialNewsData?) {
    val pagerState = rememberPagerState(pageCount = { newsData?.list?.size ?: 0 })
    val coroutineScope = rememberCoroutineScope()
    Column(
        Modifier.background(
            AppTheme.colors.surfaceContainer, RoundedCornerShape(AppTheme.radius.contentCard)
        ).padding(bottom = 8.dp).clip(
            RoundedCornerShape(AppTheme.radius.contentCard)
        ), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HorizontalPager(modifier = Modifier, state = pagerState) { currentPager ->
            NewsPagerCardItem(newsData?.list?.get(currentPager))
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
fun NewsPagerCardItem(news: OfficialNewsListItem?) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(1.7f)
    ) {
        if (news == null) {
            ImageNotFound()
        } else {
            val urlHandler = LocalUriHandler.current
            AsyncImage(
                modifier = Modifier.fillMaxSize().clickable(
                    interactionSource = interactionSource, indication = ripple(radius = 16.dp)
                ) {
                    urlHandler.openUri("https://zenless.hoyoverse.com/en-us/news/${news.getNewsId()}")
                },
                model = news.getImageUrl(),
                contentDescription = news.getDescription(),
                contentScale = ContentScale.Crop
            )
            if (isPressed.value || isHovered.value) {
                NewsInfo(Modifier.align(Alignment.BottomCenter), news)
            }
        }
    }
}

@Composable
private fun NewsInfo(modifier: Modifier, news: OfficialNewsListItem) {
    Column(
        modifier.fillMaxWidth().background(AppTheme.colors.surface.copy(alpha = 0.9f))
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = news.getTitle(),
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = news.getDescription(),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = news.getDate(),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium,
            textAlign = TextAlign.End
        )
    }
}