/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzSlider
import ui.components.buttons.ZzzOutlineButton
import ui.components.buttons.ZzzPrimaryButton
import ui.components.items.RarityItem
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.apply
import zzzarchive.composeapp.generated.resources.default_value
import zzzarchive.composeapp.generated.resources.font_scale
import zzzarchive.composeapp.generated.resources.img_summer_solstice_2023_artwork
import zzzarchive.composeapp.generated.resources.multiply
import zzzarchive.composeapp.generated.resources.ui_scale
import zzzarchive.composeapp.generated.resources.ui_scale_warning
import kotlin.math.round

@Composable
fun ScaleFontSizeDialog(
    uiScaleValue: Float,
    fontScaleValue: Float,
    onApply: (Float, Float) -> Unit,
    onDismiss: () -> Unit
) {
    var uiScale by AppTheme.uiScale
    var fontScale by AppTheme.fontScale
    Dialog(onDismissRequest = {
        uiScale = uiScaleValue
        fontScale = fontScaleValue
        onDismiss()
    }) {
        Card(
            modifier = Modifier.widthIn(max = 512.dp), colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.surfaceContainer,
                contentColor = AppTheme.colors.onSurfaceContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 32.dp, top = 32.dp, end = 32.dp, bottom = 16.dp
                ), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    RarityItem(name = "", placeHolder = {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.img_summer_solstice_2023_artwork),
                            contentDescription = null
                        )
                    })
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.ui_scale_warning),
                        style = AppTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = stringResource(Res.string.ui_scale) + " " + stringResource(Res.string.multiply) + ": ${
                        round(
                            uiScale * 10
                        ) / 10
                    }x"
                )
                ZzzSlider(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiScale,
                    onValueChange = { uiScale = it },
                    valueRange = 0.5f..1.5f,
                    steps = 9
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = stringResource(Res.string.font_scale) + " " + stringResource(Res.string.multiply) + ": ${
                        round(
                            fontScale * 10
                        ) / 10
                    }x"
                )
                ZzzSlider(
                    modifier = Modifier.fillMaxWidth(),
                    value = fontScale,
                    onValueChange = { fontScale = it },
                    valueRange = 0.5f..1.5f,
                    steps = 9,
                )
                Spacer(Modifier.size(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                ) {
                    ZzzOutlineButton(text = stringResource(Res.string.default_value)) {
                        uiScale = 1f
                        fontScale = 1f
                    }
                    ZzzPrimaryButton(text = stringResource(Res.string.apply)) {
                        onApply(uiScale, fontScale)
                        onDismiss()
                    }
                }
            }
        }
    }
}
