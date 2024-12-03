/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.components

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_bangboo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZzzSlider(
    modifier: Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    @IntRange(from = 0) steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    Slider(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        steps = steps,
        colors = SliderDefaults.colors(
            thumbColor = AppTheme.colors.primary,
            activeTrackColor = AppTheme.colors.primary,
            inactiveTrackColor = AppTheme.colors.surface,
            inactiveTickColor = AppTheme.colors.primary
        ),
        thumb = {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = vectorResource(Res.drawable.ic_bangboo),
                contentDescription = null,
                tint = AppTheme.colors.primary
            )
        })
}