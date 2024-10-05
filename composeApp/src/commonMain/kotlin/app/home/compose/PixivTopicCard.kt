/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import app.home.model.RecentArticle
import app.home.model.pixivTagDropdownItems
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.size.Size
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.component.CardHeader
import ui.component.ContentCard
import ui.component.ImageNotFound
import ui.component.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_arrow_next
import zzzarchive.composeapp.generated.resources.ic_favorite
import zzzarchive.composeapp.generated.resources.pixiv_hot
import zzzarchive.composeapp.generated.resources.previous
import zzzarchive.composeapp.generated.resources.unknown

@Composable
fun PixivTopicCard(
    recentArticlesList: List<RecentArticle>, adaptiveLayoutType: AdaptiveLayoutType,
    onPixivTagChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered.value, lazyListState, recentArticlesList, onPixivTagChange)
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
            )
        ) {
            items(items = recentArticlesList, key = { it.id }) { item ->
                PixivTopicItem(
                    adaptiveLayoutType,
                    artworkId = item.id,
                    artworkName = item.title ?: stringResource(Res.string.unknown),
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
private fun Header(
    isHovered: Boolean,
    lazyListState: LazyListState,
    recentArticlesList: List<RecentArticle>,
    onPixivTagChange: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    CardHeader(
        modifier = Modifier.fillMaxWidth(), titleRes = Res.string.pixiv_hot
    ) {
        Spacer(Modifier.size(8.dp))
        TagDropDownButton(onPixivTagChange = {
            onPixivTagChange(it)
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
            }
        })
        Spacer(Modifier.weight(1f))
        AnimatedVisibility(visible = isHovered, enter = fadeIn(), exit = fadeOut()){
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_back,
                    textRes = Res.string.previous,
                    size = 32.dp
                ) {
                    val targetIndex = lazyListState.firstVisibleItemIndex - 3
                    coroutineScope.launch {
                        if (targetIndex >= 0) {
                            lazyListState.animateScrollToItem(targetIndex)
                        } else {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                }
                ZzzIconButton(
                    iconRes = Res.drawable.ic_arrow_next,
                    textRes = Res.string.previous,
                    size = 32.dp
                ) {
                    val targetIndex = lazyListState.firstVisibleItemIndex + 3
                    coroutineScope.launch {
                        if (targetIndex < recentArticlesList.size) {
                            lazyListState.animateScrollToItem(targetIndex)
                        } else {
                            lazyListState.animateScrollToItem(recentArticlesList.size - 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TagDropDownButton(onPixivTagChange: (String) -> Unit) {
    Column {
        val pixivZzzLikeTags = pixivTagDropdownItems
        var showTagsList by remember { mutableStateOf(false) }
        var tagText by remember { mutableStateOf(pixivZzzLikeTags.first().tagText) }
        Row(
            modifier = Modifier.clickable { showTagsList = true }.background(
                AppTheme.colors.surface, RoundedCornerShape(8.dp)
            ).padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
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
    adaptiveLayoutType: AdaptiveLayoutType,
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
        modifier = Modifier.width(if (adaptiveLayoutType == AdaptiveLayoutType.Compact) 120.dp else 160.dp),
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
