package ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.under_development

@Composable
fun UnderDevelopScreen() {
    Box {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(Res.string.under_development),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.headlineMedium
        )
        DVDScreensaver(
            modifier = Modifier.fillMaxSize(), colors = listOf(
                AppTheme.colors.primary,
                AppTheme.colors.onSurfaceVariant,
                AppTheme.colors.secondary,
                AppTheme.colors.onSurface
            ), imageSize = 64
        )
    }
}