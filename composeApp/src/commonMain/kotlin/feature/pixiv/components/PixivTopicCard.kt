/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.size.Size
import feature.home.model.pixivTagDropdownItems
import feature.pixiv.data.RecentArticle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_favorite
import zzzarchive.composeapp.generated.resources.pixiv_hot
import zzzarchive.composeapp.generated.resources.unknown

@Composable
fun PixivTopicCard(
    recentArticlesList: List<RecentArticle>,
    onPixivTagChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered.value, lazyListState, onPixivTagChange)
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = 24.dp
            ), horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapImageProfileList)
        ) {
            items(items = recentArticlesList, key = { it.id }) { item ->
                PixivTopicItem(
                    artworkId = item.id,
                    artworkName = item.title ?: stringResource(Res.string.unknown),
                    artworkUrl = item.url,
                    profileId = item.userId,
                    profileName = item.userName,
                    profileUrl = item.profileImageUrl
                )
            }
        }
    }
}

@Composable
private fun Header(
    isHovered: Boolean,
    lazyListState: LazyListState,
    onPixivTagChange: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    HoveredIndicatorHeader(
        title = stringResource(Res.string.pixiv_hot),
        isHovered = isHovered,
        lazyListState = lazyListState,
        startContent = {
            TagDropDownButton(onPixivTagChange = {
                onPixivTagChange(it)
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            })
            Spacer(Modifier.weight(1f))
        }
    )
}

@Composable
private fun TagDropDownButton(onPixivTagChange: (String) -> Unit) {
    Column {
        val pixivZzzLikeTags = pixivTagDropdownItems
        var showTagsList by remember { mutableStateOf(false) }
        var tagText by remember { mutableStateOf(pixivZzzLikeTags.first().tagText) }
        Row(modifier = Modifier.clip(RoundedCornerShape(8.dp)).clickable { showTagsList = true }
            .pointerHoverIcon(PointerIcon.Hand).background(AppTheme.colors.surface)
            .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = vectorResource(Res.drawable.ic_favorite),
                contentDescription = null,
                tint = AppTheme.colors.onSurface
            )
            Text(
                text = tagText,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurface
            )
        }
        DropdownMenu(expanded = showTagsList,
            containerColor = AppTheme.colors.surface,
            onDismissRequest = { showTagsList = false }) {
            pixivZzzLikeTags.forEach { tag ->
                DropdownMenuItem(text = {
                    Text(
                        text = tag.tagText,
                        style = AppTheme.typography.labelMedium,
                        color = AppTheme.colors.onSurface
                    )
                }, onClick = {
                    onPixivTagChange(tag.tagOnPixiv)
                    tagText = tag.tagText
                    showTagsList = false
                })
            }
        }
    }
}

@Composable
private fun PixivTopicItem(
    artworkId: String,
    artworkName: String,
    artworkUrl: String?,
    profileId: String,
    profileName: String,
    profileUrl: String?
) {
    val interactionSource = remember { MutableInteractionSource() }
    val urlHandler = LocalUriHandler.current
    val header = NetworkHeaders.Builder().add("Referer", "https://app-api.pixiv.net/").build()
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current).httpHeaders(header)
            .data(artworkUrl).size(Size.ORIGINAL).build()
    )
    Column(
        modifier = Modifier.width(AppTheme.fixedSize.galleryItemSize),
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
    profileName: String, profileUrl: String?, header: NetworkHeaders, onClick: () -> Unit
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
            modifier = Modifier.size(AppTheme.fixedSize.iconSize).aspectRatio(1f).clip(CircleShape)
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
