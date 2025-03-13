/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.theme.AppTheme

@Composable
fun ZzzSwitch(checkState: Boolean, onCheckChange: (Boolean) -> Unit) {
    Switch(
        checked = checkState,
        onCheckedChange = { onCheckChange(it) },
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = AppTheme.colors.buttonBorder,
            uncheckedBorderColor = AppTheme.colors.buttonBorder,
            uncheckedTrackColor = Color.Companion.Transparent,
            checkedThumbColor = Color.Companion.White,
            checkedTrackColor = AppTheme.colors.primary
        )
    )
}