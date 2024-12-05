/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

sealed interface SettingAction {
    data class ChangeToDarkTheme(val isDark: Boolean) : SettingAction
    data class ChangeLanguage(val langCode: String) : SettingAction
    data class ScaleUi(val uiScale: Float, val fontScale: Float) : SettingAction
    data object ClickFeedback : SettingAction
    data object RestartApp : SettingAction
}