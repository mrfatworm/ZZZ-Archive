package setting

import utils.Language

interface SettingsRepository {
    fun getIsDarkTheme(): Boolean
    fun setIsDarkTheme(value: Boolean)
    fun getLanguage(): Language
    fun setLanguage(value: Language)
    fun clear()
}