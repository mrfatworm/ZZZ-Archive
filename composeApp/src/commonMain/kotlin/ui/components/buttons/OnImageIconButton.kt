package ui.components.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme

@Composable
fun OnImageIconButton(
    iconRes: DrawableResource,
    contentDescriptionRes: StringResource,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    containerColor: Color = AppTheme.colors.hoveredMask,
    tint: Color = AppTheme.colors.onHoveredMask,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .size(AppTheme.size.iconButtonSmall)
            .pointerHoverIcon(PointerIcon.Hand),
        colors =
            IconButtonDefaults.iconButtonColors().copy(
                containerColor = containerColor,
                contentColor = tint
            ),
        interactionSource = interactionSource,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.size.icon),
            imageVector = vectorResource(iconRes),
            contentDescription = stringResource(contentDescriptionRes)
        )
    }
}
