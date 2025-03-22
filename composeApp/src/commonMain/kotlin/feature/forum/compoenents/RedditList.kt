package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import feature.forum.model.RedditForumListState
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_reddit_comment
import zzzarchive.composeapp.generated.resources.ic_up_vote

@Composable
fun RedditList(redditList: List<RedditForumListState>) {
    val urlHandler = LocalUriHandler.current
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
        for (index in redditList.indices) {
            RedditListItem(modifier = Modifier.clickable {
                urlHandler.openUri(redditList[index].link)
            }, reddit = redditList[index], isVariantColor = index % 2 == 0)
        }
    }
}

@Composable
private fun RedditListItem(
    modifier: Modifier = Modifier, reddit: RedditForumListState, isVariantColor: Boolean = false
) {
    Row(
        modifier = modifier
            .background(if (isVariantColor) AppTheme.colors.itemVariant else AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
    ) {
        ForumThumbnailImage(reddit.imgUrl)
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                text = reddit.title,
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
                Icon(
                    modifier = Modifier.size(AppTheme.size.icon),
                    imageVector = vectorResource(Res.drawable.ic_up_vote),
                    contentDescription = "Up Vote",
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = reddit.upVotes,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.icon),
                    imageVector = vectorResource(Res.drawable.ic_reddit_comment),
                    contentDescription = "Comment",
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = reddit.comments,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
    }
}