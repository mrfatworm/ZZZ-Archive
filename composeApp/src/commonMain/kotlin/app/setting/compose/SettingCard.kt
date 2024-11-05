/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.compose

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
import androidx.compose.ui.unit.dp
import app.setting.model.SettingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.components.dialogs.RestartDialog
import ui.theme.AppTheme
import utils.Language
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.color_theme
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.hoyolab_account
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios
import zzzarchive.composeapp.generated.resources.ic_arrow_next_ios
import zzzarchive.composeapp.generated.resources.language
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.under_development

@Composable
fun SettingCard(
    uiState: SettingState,
    onLanguageChange: (String) -> Unit,
    onColorChange: (Boolean) -> Unit,
    onRestart: () -> Unit = {}
) {
    ContentCard(hasDefaultPadding = false) {
        LanguageSettingItem(uiState.language, onLanguageChange, onRestart)
        ColorSettingItem(onColorChange)
        HoYoLabSettingItem()
    }
}

@Composable
private fun LanguageSettingItem(
    language: Language,
    onLanguageChange: (String) -> Unit,
    onRestart: () -> Unit = {}
) {
    var showLanguageList by remember { mutableStateOf(false) }
    val openRestartDialog = remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.language), content = {
        Column(horizontalAlignment = Alignment.End) {
            val languagesList = Language.entries.toList()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = language.localName,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(12.dp),
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
                        onLanguageChange(languageItem.project)
                        showLanguageList = false
                    })
                }
            }
        }
    }, onClick = { showLanguageList = true })
    when {
        openRestartDialog.value -> {
            RestartDialog(onRestart = onRestart, onDismiss = { openRestartDialog.value = false })
        }
    }
}

@Composable
private fun ColorSettingItem(onColorChange: (Boolean) -> Unit) {
    var showColorThemeList by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.color_theme), content = {
        Column(horizontalAlignment = Alignment.End) {
            var isDarkTheme by AppTheme.isDark
            val colorThemeList = listOf(Res.string.dark_theme, Res.string.light_theme)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (isDarkTheme) stringResource(Res.string.dark_theme) else stringResource(
                        Res.string.light_theme
                    ),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showColorThemeList,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showColorThemeList = false }) {
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
                        isDarkTheme = isDark
                        showColorThemeList = false
                    })
                }
            }
        }
    }, onClick = { showColorThemeList = true })
}

@Composable
private fun HoYoLabSettingItem() {
    SettingItem(title = stringResource(Res.string.hoyolab_account), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(Res.string.under_development),
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurface
            )
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
    }, onClick = { })
}