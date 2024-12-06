/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzOutlineButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.announcement
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.ic_link

@Composable
fun BannerDialog(
    message: String,
    url: String,
    urlDesc: String,
    route: String,
    routeDesc: String,
    onNavigate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    BasicDialog(onDismissRequest = onDismiss) {
        Box(
            Modifier.fillMaxWidth()
                .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.announcement),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleMedium
            )
            ZzzIconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                iconRes = Res.drawable.ic_close,
                contentDescriptionRes = Res.string.close
            ) {
                onDismiss()
            }
        }
        Column(
            Modifier.padding(
                start = AppTheme.spacing.s500,
                end = AppTheme.spacing.s500,
                bottom = AppTheme.spacing.s400
            )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().heightIn(min = 64.dp),
                text = message,
                style = AppTheme.typography.bodyMedium
            )
            if (url != "") {
                val urlHandler = LocalUriHandler.current
                ZzzOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    iconRes = Res.drawable.ic_link,
                    text = urlDesc
                ) {
                    urlHandler.openUri(url)
                }
            }

            if (route != "") {
                ZzzOutlineButton(
                    modifier = Modifier.fillMaxWidth(), text = routeDesc
                ) {
                    onNavigate(route)
                    onDismiss()
                }
            }
        }
    }
}

