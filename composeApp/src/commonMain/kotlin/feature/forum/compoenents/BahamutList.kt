package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import feature.forum.model.BahamutForumListState
import org.jetbrains.compose.resources.vectorResource
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
        modifier = modifier
            .background(if (isVariantColor) AppTheme.colors.itemVariant else AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
    ) {
        ForumThumbnailImage(bahamut.imgUrl)
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
            Text(
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
                if (bahamut.category.isNotEmpty()) {
                    Text(
                        modifier = Modifier.clip(CircleShape).background(AppTheme.colors.surface)
                            .padding(
                                horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                            ),
                        text = bahamut.category,
                        color = AppTheme.colors.onSurface,
                        style = AppTheme.typography.labelSmall
                    )
                }
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

