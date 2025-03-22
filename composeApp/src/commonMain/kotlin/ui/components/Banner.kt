package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import feature.banner.data.BannerLevel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close

@Composable
fun Banner(
    modifier: Modifier,
    title: String,
    bannerLevel: BannerLevel,
    closable: Boolean,
    actionTextRes: StringResource? = null,
    onActionClicked: () -> Unit,
    onClosed: () -> Unit
) {
    val containerColor = when (bannerLevel) {
        BannerLevel.Normal -> AppTheme.colors.primaryContainer
        BannerLevel.Warning -> AppTheme.colors.secondary
        BannerLevel.Alert -> AppTheme.colors.alert
    }
    val contentColor = when (bannerLevel) {
        BannerLevel.Normal -> AppTheme.colors.onPrimaryContainer
        BannerLevel.Warning -> AppTheme.colors.onSecondary
        BannerLevel.Alert -> AppTheme.colors.onAlert
    }

    Row(
        modifier = modifier.clip(AppTheme.shape.r400).fillMaxWidth()
            .background(containerColor)
            .padding(start = AppTheme.spacing.s400, end = AppTheme.spacing.s200),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = contentColor,
            style = AppTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        actionTextRes?.let {
            Text(
                modifier = Modifier.clickable(onClick = onActionClicked)
                    .padding(vertical = AppTheme.spacing.s200),
                text = stringResource(actionTextRes),
                color = contentColor,
                style = AppTheme.typography.titleSmall,
                textDecoration = TextDecoration.Underline
            )
        }
        if (closable) {
            IconButton(onClick = onClosed) {
                Icon(
                    modifier = Modifier.size(AppTheme.size.icon),
                    imageVector = vectorResource(Res.drawable.ic_close),
                    contentDescription = stringResource(Res.string.close),
                    tint = contentColor
                )
            }
        }
    }
}