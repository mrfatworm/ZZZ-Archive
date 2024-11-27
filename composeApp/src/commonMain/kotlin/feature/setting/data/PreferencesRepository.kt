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
    fun clear()
}