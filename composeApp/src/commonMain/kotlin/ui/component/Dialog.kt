package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.LocalPlatformContext
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import utils.restartApp
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.announcement
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.ic_link
import zzzarchive.composeapp.generated.resources.later
import zzzarchive.composeapp.generated.resources.restart
import zzzarchive.composeapp.generated.resources.restart_hint

@Composable
fun BannerDialog(message: String, url: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.widthIn(max = 512.dp).heightIn(max = 512.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.surfaceContainer,
                contentColor = AppTheme.colors.onSurfaceContainer
            )
        ) {
            Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
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
                Modifier.padding(start = 32.dp, end = 32.dp, bottom = 16.dp)
                    .verticalScroll(rememberScrollState())
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
                        text = url
                    ) {
                        urlHandler.openUri(url)
                    }
                }
            }
        }
    }
}

@Composable
fun RestartDialog(onDismiss: () -> Unit, onRestart: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.widthIn(max = 320.dp).heightIn(max = 320.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.surfaceContainer,
                contentColor = AppTheme.colors.onSurfaceContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 32.dp, top = 32.dp, end = 32.dp, bottom = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.restart_hint),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.bodyMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val platformContext = LocalPlatformContext.current
                    ZzzOutlineButton(text = stringResource(Res.string.later)) {
                        onDismiss()
                    }
                    ZzzPrimaryButton(text = stringResource(Res.string.restart)) {
                        restartApp(platformContext)
                    }
                }
            }
        }
    }
}