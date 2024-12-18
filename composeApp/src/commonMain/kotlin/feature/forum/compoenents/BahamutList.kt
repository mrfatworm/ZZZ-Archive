package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import feature.forum.model.BahamutForumListState
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_like

@Composable
fun BahamutList(bahamutList: List<BahamutForumListState>) {
    val urlHandler = LocalUriHandler.current
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
        for (index in bahamutList.indices) {
            BahamutListItem(modifier = Modifier.clickable {
                urlHandler.openUri(bahamutList[index].link)
            }, bahamut = bahamutList[index], isVariantColor = index % 2 == 0)
        }
    }
}

@Composable
private fun BahamutListItem(
    modifier: Modifier = Modifier,
    bahamut: BahamutForumListState,
    isVariantColor: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(if (isVariantColor) AppTheme.colors.itemVariant else AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
    ) {
        Box(
            modifier = Modifier.height(AppTheme.size.rarityItemSmallSize).aspectRatio(1.33f)
                .clip(AppTheme.shape.r300)
        ) {
            AsyncImage(
                modifier = Modifier.matchParentSize().blur(8.dp),
                model = bahamut.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.None
            )
            SubcomposeAsyncImage(
                modifier = Modifier.matchParentSize(),
                model = bahamut.imgUrl,
                contentDescription = null,
                filterQuality = FilterQuality.None,
                error = {
                    ImageNotFound()
                })
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = bahamut.title,
                color = AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.bodyMedium,
                minLines = 2,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
                    AppTheme.spacing.s300, Alignment.End
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.clip(CircleShape).background(AppTheme.colors.surface)
                        .padding(
                            horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                        ),
                    text = bahamut.category,
                    color = AppTheme.colors.onSurface,
                    style = AppTheme.typography.labelSmall
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconSize),
                    imageVector = vectorResource(Res.drawable.ic_like),
                    contentDescription = "GP",
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = bahamut.gp,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
    }
}