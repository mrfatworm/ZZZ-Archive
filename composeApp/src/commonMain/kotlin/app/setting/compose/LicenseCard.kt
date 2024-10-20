/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrfatworm.zzzarchive.ZzzConfig
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.code_licence
import zzzarchive.composeapp.generated.resources.resource_licence

@Composable
fun LicenseCard() {
    ContentCard {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.app_name),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.titleSmall,
            color = AppTheme.colors.onSurfaceVariant
        )
        Spacer(Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = ZzzConfig.VERSION,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.labelSmall,
            color = AppTheme.colors.onSurfaceVariant
        )
        Spacer(Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.resource_licence),
            style = AppTheme.typography.bodySmall,
            color = AppTheme.colors.onSurfaceVariant
        )
        Spacer(Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.code_licence),
            style = AppTheme.typography.bodySmall,
            color = AppTheme.colors.onSurfaceVariant
        )
    }
}