/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzOutlineButton
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.apply
import zzzarchive.composeapp.generated.resources.default_value
import zzzarchive.composeapp.generated.resources.multiply
import zzzarchive.composeapp.generated.resources.ui_scale_warning
import kotlin.math.round

@Composable
fun ScaleFontSizeDialog(currentScaleValue: Float, onApply: (Float) -> Unit, onDismiss: () -> Unit) {
    var scaleFont by AppTheme.fontScale
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
                    text = stringResource(Res.string.ui_scale_warning),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.bodyMedium
                )

                Text(text = stringResource(Res.string.multiply) + ": ${round(scaleFont * 10) / 10} x")

                Slider(
                    value = scaleFont,
                    onValueChange = { scaleFont = it },
                    valueRange = 0.5f..1.5f,
                    steps = 9,
                    colors = SliderDefaults.colors(
                        thumbColor = AppTheme.colors.primary,
                        activeTrackColor = AppTheme.colors.primary,
                        inactiveTrackColor = AppTheme.colors.surface,
                        inactiveTickColor = AppTheme.colors.primary
                    ),
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)) {
                    ZzzOutlineButton(text = stringResource(Res.string.default_value)) {
                        scaleFont = 1f
                    }
                    ZzzPrimaryButton(text = stringResource(Res.string.apply)) {
                        onApply(scaleFont)
                    }
                }
            }
        }
    }
}
