/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.setting.model.SettingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.components.dialogs.DoubleActionDialog
import ui.components.dialogs.ScaleFontSizeDialog
import ui.theme.AppTheme
import utils.Language
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.color_theme
import zzzarchive.composeapp.generated.resources.customize
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.default_value
import zzzarchive.composeapp.generated.resources.hoyolab_account
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios
import zzzarchive.composeapp.generated.resources.ic_arrow_next_ios
import zzzarchive.composeapp.generated.resources.language
import zzzarchive.composeapp.generated.resources.later
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.restart
import zzzarchive.composeapp.generated.resources.restart_hint
import zzzarchive.composeapp.generated.resources.ui_scale

@Composable
fun SettingCard(
    uiState: SettingState,
    onLanguageChange: (String) -> Unit,
    onColorChange: (Boolean) -> Unit,
    onScaleChange: (Float, Float) -> Unit,
    onHoYoLabClick: () -> Unit,
    onRestart: () -> Unit
) {
    ContentCard(hasDefaultPadding = false) {
        LanguageSettingItem(uiState.language, onLanguageChange, onRestart)
        ColorSettingItem(uiState.isDark, onColorChange)
        FontScaleItem(uiState.uiScale, uiState.fontScale, onScaleChange)
        HoYoLabSettingItem(onHoYoLabClick)
    }
}

@Composable
private fun LanguageSettingItem(
    language: Language,
    onLanguageChange: (String) -> Unit,
    onRestart: () -> Unit
) {
    var showLanguageList by remember { mutableStateOf(false) }
    val openRestartDialog = remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.language), content = {
        Column(horizontalAlignment = Alignment.End) {
            val languagesList = Language.entries.toList()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                Text(
                    text = language.localName,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconSmall),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showLanguageList,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showLanguageList = false }) {
                languagesList.forEach { languageItem ->
                    DropdownMenuItem(text = {
                        Text(
                            text = languageItem.localName,
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        if (languageItem.name != language.name) {
                            openRestartDialog.value = true
                        }
                        onLanguageChange(languageItem.code)
                        showLanguageList = false
                    })
                }
            }
        }
    }, onClick = { showLanguageList = true })
    when {
        openRestartDialog.value -> {
            DoubleActionDialog(
                text = stringResource(Res.string.restart_hint),
                primaryActionText = stringResource(Res.string.restart),
                secondaryActionText = stringResource(Res.string.later),
                onPrimaryAction = {
                    openRestartDialog.value = false
                    onRestart()
                },
                onSecondaryAction = {
                    openRestartDialog.value = false
                },
                onDismiss = {
                    // Cannot dismiss
                }
            )
        }
    }
}

@Composable
private fun FontScaleItem(
    uiScaleValue: Float,
    fontScaleValue: Float,
    onScaleChange: (Float, Float) -> Unit,
) {
    val openUiScaleDialog = remember { mutableStateOf(false) }
    val actionText = if (uiScaleValue == 1f && fontScaleValue == 1f) {
        stringResource(Res.string.default_value)
    } else {
        stringResource(Res.string.customize)
    }
    SettingItem(title = stringResource(Res.string.ui_scale), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                text = actionText,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurface
            )
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSmall),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
    }, onClick = { openUiScaleDialog.value = true })
    when {
        openUiScaleDialog.value -> {
            ScaleFontSizeDialog(
                uiScaleValue = uiScaleValue,
                fontScaleValue = fontScaleValue,
                onApply = { uiScale, fontScale ->
                    onScaleChange(uiScale, fontScale)
                },
                onDismiss = {
                    openUiScaleDialog.value = false
                })
        }
    }
}

@Composable
private fun ColorSettingItem(isDarkTheme: Boolean, onColorChange: (Boolean) -> Unit) {
    var showColorThemeDropdown by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.color_theme), content = {
        Column(horizontalAlignment = Alignment.End) {
            //var isDarkTheme by AppTheme.isDark
            val colorThemeList = listOf(Res.string.dark_theme, Res.string.light_theme)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                Text(
                    text = if (isDarkTheme) stringResource(Res.string.dark_theme) else stringResource(
                        Res.string.light_theme
                    ),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.iconSmall),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showColorThemeDropdown,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showColorThemeDropdown = false }) {
                colorThemeList.forEach { colorTheme ->
                    DropdownMenuItem(text = {
                        Text(
                            text = stringResource(colorTheme),
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        val isDark = colorTheme == Res.string.dark_theme
                        onColorChange(isDark)
                        showColorThemeDropdown = false
                    })
                }
            }
        }
    }, onClick = { showColorThemeDropdown = true })
}

@Composable
private fun HoYoLabSettingItem(onClick: () -> Unit) {
    SettingItem(title = stringResource(Res.string.hoyolab_account), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSmall),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
    }, onClick = onClick)
}