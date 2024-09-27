/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_arrow_next
import zzzarchive.composeapp.generated.resources.view_all

@Composable
fun ContentCard(
    modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.background(
            AppTheme.colors.surfaceContainer, RoundedCornerShape(AppTheme.radius.contentCard)
        )
    ) {
        content()
    }
}

@Composable
fun ViewAllCardHeader(modifier: Modifier, titleRes: StringResource, onActionClick: () -> Unit) {
    CardHeader(
        modifier = modifier
            .fillMaxWidth(),
        titleRes = titleRes
    ) {
        Row(
            modifier = Modifier.clickable { onActionClick() },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.view_all),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleSmall
            )
            Icon(
                imageVector = vectorResource(Res.drawable.ic_arrow_next),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CardHeader(
    modifier: Modifier, titleRes: StringResource, action: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(titleRes),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
        action()
    }
}