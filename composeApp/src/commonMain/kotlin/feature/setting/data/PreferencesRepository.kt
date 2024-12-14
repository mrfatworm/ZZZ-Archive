/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

interface PreferencesRepository {
    fun getIsDarkTheme(): Boolean
    fun setIsDarkTheme(value: Boolean)
    fun getLanguageCode(): String
    fun setLanguage(langCode: String)
    fun getUiScale(): Float
    fun setUiScale(value: Float)
    fun getFontScale(): Float
    fun setFontScale(value: Float)
    fun getDefaultHoYoLabAccountUid(): Int
    fun setDefaultHoYoLabAccountUid(value: Int)
    fun clear()
}