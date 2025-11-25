/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.round
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

@Composable
fun ScaleFontSizeDialog(
    modifier: Modifier = Modifier,
    uiScaleValue: Float,
    fontScaleValue: Float,
    onApply: (Float, Float) -> Unit,
    onDismiss: () -> Unit
) {
    val uiScale = AppTheme.uiScale
    val fontScale = AppTheme.fontScale
    val themeController = AppTheme.themeController
    BasicDialog(modifier = modifier.widthIn(max = 720.dp), onDismissRequest = {
        themeController.setUiScale(uiScaleValue)
        themeController.setFontScale(fontScaleValue)
        onDismiss()
    }) {
        Column(
            modifier =
                Modifier.padding(
                    start = AppTheme.spacing.s500,
                    top = AppTheme.spacing.s500,
                    end = AppTheme.spacing.s500,
                    bottom = AppTheme.spacing.s400
                ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s450)
        ) {
            ScalePreview()
            ScaleUiSlider(stringResource(Res.string.ui_scale), uiScale, onScaleValueChange = {
                themeController.setUiScale(it)
            })
            ScaleUiSlider(stringResource(Res.string.font_scale), fontScale, onScaleValueChange = {
                themeController.setFontScale(it)
            })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = AppTheme.spacing.s300, alignment = Alignment.End)
            ) {
                ZzzOutlineButton(text = stringResource(Res.string.default_value)) {
                    themeController.setUiScale(1f)
                    themeController.setFontScale(1f)
                }
                ZzzPrimaryButton(text = stringResource(Res.string.apply)) {
                    onApply(uiScale, fontScale)
                    onDismiss()
                }
            }
        }
    }
}

@Composable
private fun ScalePreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)) {
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
}

@Composable
private fun ScaleUiSlider(
    scaleType: String,
    scaleValue: Float,
    onScaleValueChange: (Float) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
        val firstDigitPlace = round(scaleValue * 10) / 10
        Text(
            text = "$scaleType ${stringResource(Res.string.multiply)}: ${firstDigitPlace}x"
        )
        ZzzSlider(
            modifier = Modifier.fillMaxWidth(),
            value = scaleValue,
            onValueChange = onScaleValueChange,
            valueRange = 0.5f..1.5f,
            steps = 9
        )
    }
}
