/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.forum.compoenents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import feature.forum.model.TwitterForumListState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.theme.AppTheme
import ui.utils.cardPaddingWithHeader
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_twitter
import zzzarchive.composeapp.generated.resources.popular

@Composable
fun TwitterCard(twitterList: List<TwitterForumListState>) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered.value, lazyListState)
        LazyRow(
            state = lazyListState,
            contentPadding = cardPaddingWithHeader(),
            horizontalArrangement = rowListGap()
        ) {
            items(items = twitterList) { item ->
                TwitterListItem(
                    imageUrl = item.imgUrl,
                    artworkName = item.title,
                    artworkUrl = item.link,
                    profileUrl = item.authorUrl,
                    profileName = item.author
                )
            }
        }
    }
}

@Composable
private fun Header(isHovered: Boolean, lazyListState: LazyListState) {
    HoveredIndicatorHeader(
        title = null, isHovered = isHovered, lazyListState = lazyListState, startContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconLarge),
                    imageVector = vectorResource(Res.drawable.ic_twitter),
                    contentDescription = "X",
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = stringResource(Res.string.popular),
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colors.onSurfaceVariant
                )
            }
        })
}

@Composable
private fun TwitterListItem(
    imageUrl: String,
    artworkName: String,
    artworkUrl: String,
    profileName: String,
    profileUrl: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val urlHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.width(AppTheme.size.s144),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1f).clip(AppTheme.shape.r400)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null) {
                    urlHandler.openUri(artworkUrl)
                }) {
            AsyncImage(
                modifier = Modifier.matchParentSize().blur(8.dp),
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                filterQuality = FilterQuality.None
            )

            SubcomposeAsyncImage(
                modifier = Modifier.matchParentSize(),
                model = imageUrl,
                contentDescription = artworkName,
                error = {
                    ImageNotFound()
                })
        }
        Text(
            modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Hand)
                .clickable(interactionSource = interactionSource, indication = null) {
                    urlHandler.openUri(artworkUrl)
                },
            text = artworkName,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
        Text(
            modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Hand).clickable {
                urlHandler.openUri(profileUrl)
            },
            text = profileName,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.bodySmall,
            color = AppTheme.colors.onSurfaceVariant,
            maxLines = 1
        )
    }
}

