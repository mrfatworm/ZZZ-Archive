package setting

interface SettingsRepository {
    fun getIsDarkTheme(): Boolean
    fun setIsDarkTheme(value: Boolean)
    fun getLanguage(): String
    fun setLanguage(langCode: String)
    fun getBannerIgnoreId(): Int
    fun setBannerIgnoreId(value: Int)
    fun clear()
}