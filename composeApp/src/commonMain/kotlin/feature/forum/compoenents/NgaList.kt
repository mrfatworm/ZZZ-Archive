package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import feature.forum.model.NgaForumListState
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.replies

@Composable
fun NgaList(ngaList: List<NgaForumListState>) {
    val urlHandler = LocalUriHandler.current
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
        for (index in ngaList.indices) {
            NgaListItem(modifier = Modifier.clickable {
                urlHandler.openUri(ngaList[index].link)
            }, nga = ngaList[index], isVariantColor = index % 2 == 0)
        }
    }
}

@Composable
private fun NgaListItem(
    modifier: Modifier = Modifier,
    nga: NgaForumListState,
    isVariantColor: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .background(if (isVariantColor) AppTheme.colors.itemVariant else AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = nga.title,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.bodyMedium,
            minLines = 1,
            maxLines = 1
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = nga.replies + " " + stringResource(Res.string.replies),
            textAlign = TextAlign.End,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodySmall
        )
    }
}