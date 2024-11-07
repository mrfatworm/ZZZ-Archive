/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.data

interface SettingsRepository {
    fun getIsDarkTheme(): Boolean
    fun setIsDarkTheme(value: Boolean)
    fun getLanguage(): String
    fun setLanguage(langCode: String)
    fun getBannerIgnoreId(): Int
    fun setBannerIgnoreId(value: Int)
    fun clear()
}