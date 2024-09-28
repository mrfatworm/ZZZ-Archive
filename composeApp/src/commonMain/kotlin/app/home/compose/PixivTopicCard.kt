/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ui.component.CardHeader
import ui.component.ContentCard
import ui.component.ImageNotFound
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.pixiv_hot

@Composable
fun PixivTopicCard(recentArticlesList: List<RecentArticle>) {
    ContentCard(modifier = Modifier.fillMaxWidth()) {
        CardHeader(
            modifier = Modifier.fillMaxWidth(),
            titleRes = Res.string.pixiv_hot,
        )
        LazyRow(
            contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = 24.dp
            )
        ) {
            items(items = recentArticlesList, key = { it.id }) { item ->
                PixivTopicItem(
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
    modifier: Modifier = Modifier.width(150.dp),
    artworkId: String,
    artworkName: String,
    artworkUrl: String?,
    profileId: String,
    profileName: String,
    profileUrl: String?,
) {

    val urlHandler = LocalUriHandler.current
    val header = NetworkHeaders.Builder().add("Referer", "https://app-api.pixiv.net/").build()
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current).httpHeaders(header)
            .data(artworkUrl).size(Size.ORIGINAL).build()
    )
    Column(
        modifier = modifier.clickable {
            urlHandler.openUri("https://www.pixiv.net/artworks/$artworkId")
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().aspectRatio(1f).clip(RoundedCornerShape(16.dp))
        ) {
            if (artworkUrl != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = imageState,
                    contentDescription = artworkName
                )
            } else {
                ImageNotFound()
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
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
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current).httpHeaders(header)
            .data(profileUrl).size(Size.ORIGINAL).build()
    )
    Row(
        modifier = Modifier.clickable(onClick = onClick),
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