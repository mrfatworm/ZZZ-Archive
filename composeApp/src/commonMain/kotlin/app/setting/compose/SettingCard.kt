/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
import ui.component.ContentCard
import ui.theme.AppTheme
import utils.Language
import zzzarchive.composeapp.generated.resources.Res
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
    onColorChange: (Boolean) -> Unit
) {
    ContentCard(hasDefaultPadding = false) {
        LanguageSettingItem(onLanguageChange)
        ColorSettingItem(onColorChange)
        HoYoLabSettingItem()
    }
}

@Composable
private fun LanguageSettingItem(onLanguageChange: (String) -> Unit) {
    var showLanguageList by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.language), content = {
        Column(horizontalAlignment = Alignment.End) {
            val languagesList = Language.entries.toList()
            var currentLanguage by remember { mutableStateOf(languagesList.first().localName) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = currentLanguage,
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
                languagesList.forEach { language ->
                    DropdownMenuItem(text = {
                        Text(
                            text = language.localName,
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        onLanguageChange(language.code)
                        currentLanguage = language.localName
                        showLanguageList = false
                    })
                }
            }
        }
    }, onClick = { showLanguageList = true })
}

@Composable
private fun ColorSettingItem(onColorChange: (Boolean) -> Unit) {
    var showColorThemeList by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.language), content = {
        Column(horizontalAlignment = Alignment.End) {
            val isDark by AppTheme.isDark
            val colorThemeList = listOf(Res.string.dark_theme, Res.string.light_theme)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (isDark) stringResource(Res.string.dark_theme) else stringResource(Res.string.light_theme),
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
                        onColorChange(colorTheme == Res.string.dark_theme)
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