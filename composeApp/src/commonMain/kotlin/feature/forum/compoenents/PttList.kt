package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import feature.forum.model.PttForumListState
import ui.theme.AppTheme

@Composable
fun PttList(pttList: List<PttForumListState>) {
    val urlHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.background(AppTheme.colors.surface).padding(AppTheme.spacing.s200),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
    ) {
        pttList.forEach { pttItem ->
            PttListItem(modifier = Modifier.clickable {
                urlHandler.openUri(pttItem.link)
            }, ptt = pttItem)
        }
    }
}

@Composable
private fun PttListItem(modifier: Modifier = Modifier, ptt: PttForumListState) {
    val popularColor = if (ptt.popularity.toInt() > 100) AppTheme.colors.alert
    else if (ptt.popularity.toInt() >= 10) AppTheme.colors.secondary
    else AppTheme.colors.primary

    Row(
        modifier = modifier.fillMaxWidth().clip(AppTheme.shape.r200)
            .background(AppTheme.colors.surfaceContainer).padding(AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.widthIn(min = AppTheme.typography.headlineSmall.fontSize.value.dp),
            text = ptt.popularity,
            textAlign = TextAlign.Center,
            color = popularColor,
            style = AppTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.weight(1f),
            text = ptt.title,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}