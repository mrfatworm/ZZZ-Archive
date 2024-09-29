package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

@Composable
fun RowListFooterItem(
    modifier: Modifier = Modifier.size(100.dp), text: String, radius: Dp = 8.dp, onClick: () -> Unit
) {
    Box(
        modifier = modifier.clickable { onClick() }.background(
            AppTheme.colors.onSurfaceVariant, RoundedCornerShape(radius)
        ).background(AppTheme.colors.hoveredMask, RoundedCornerShape(radius)).padding(8.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onHoveredMaskVariant
        )
    }
}