/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.home.model.RecentArticle
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.size.Size
import kotlinx.coroutines.launch
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.ImageNotFound
import ui.theme.AppTheme
import ui.utils.NavigationType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.pixiv_hot

@Composable
fun PixivTopicCard(
    recentArticlesList: List<RecentArticle>, navigationType: NavigationType
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource)
    ) {
        HoveredIndicatorHeader(modifier = Modifier.fillMaxWidth(),
            titleRes = Res.string.pixiv_hot,
            isHovered = isHovered.value,
            onPreviousClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex - 3
                coroutineScope.launch {
                    if (targetIndex >= 0) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            },
            onNextClick = {
                val targetIndex = lazyListState.firstVisibleItemIndex + 3
                coroutineScope.launch {
                    if (targetIndex < recentArticlesList.size) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(recentArticlesList.size - 1)
                    }
                }
            })
        LazyRow(
            modifier = Modifier.hoverable(interactionSource = interactionSource),
            state = lazyListState,
            contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = 24.dp
            )
        ) {
            items(items = recentArticlesList, key = { it.id }) { item ->
                PixivTopicItem(
                    navigationType,
                    artworkId = item.id,
                    artworkName = item.title,
                    artworkUrl = item.url,
                    profileId = item.userId,
                    profileName = item.userName,
                    profileUrl = item.profileImageUrl
                )
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
}

@Composable
private fun PixivTopicItem(
    navigationType: NavigationType,
    artworkId: String,
    artworkName: String,
    artworkUrl: String?,
    profileId: String,
    profileName: String,
    profileUrl: String?,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val urlHandler = LocalUriHandler.current
    val header = NetworkHeaders.Builder().add("Referer", "https://app-api.pixiv.net/").build()
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current).httpHeaders(header)
            .data(artworkUrl).size(Size.ORIGINAL).build()
    )
    Column(
        modifier = Modifier.width(if (navigationType == NavigationType.BOTTOM_NAVIGATION) 120.dp else 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1f).clip(RoundedCornerShape(16.dp))
        ) {
            if (artworkUrl != null) {
                Image(
                    modifier = Modifier.fillMaxSize().pointerHoverIcon(PointerIcon.Hand)
                        .clickable(interactionSource = interactionSource, indication = null) {
                            urlHandler.openUri("https://www.pixiv.net/artworks/$artworkId")
                        }, painter = imageState, contentDescription = artworkName
                )
            } else {
                ImageNotFound()
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null) {
                    urlHandler.openUri("https://www.pixiv.net/artworks/$artworkId")
                },
            text = artworkName,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
        AuthorInfo(profileName, profileUrl, header, onClick = {
            urlHandler.openUri("https://www.pixiv.net/users/$profileId")
        })
    }
}

@Composable
private fun AuthorInfo(
    profileName: String, profileUrl: String?, header: NetworkHeaders, onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current).httpHeaders(header)
            .data(profileUrl).size(Size.ORIGINAL).build()
    )
    Row(
        modifier = Modifier.clickable(
            interactionSource = interactionSource, indication = null, onClick = onClick
        ).pointerHoverIcon(PointerIcon.Hand),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(18.dp).aspectRatio(1f).clip(CircleShape)
        ) {
            if (profileUrl != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = imageState,
                    contentDescription = profileName
                )
            } else {
                ImageNotFound()
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = profileName,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.bodySmall,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
    }
}