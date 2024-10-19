package setting

import utils.Language

class FakeSettingRepository : SettingsRepository {
    private var isDarkTheme = true
    private var language = Language.English
    private var bannerIgnoreId = 0

    override fun getIsDarkTheme(): Boolean {
        return isDarkTheme
    }

    override fun setIsDarkTheme(value: Boolean) {
        isDarkTheme = value
    }

    override fun getLanguage(): Language {
        return language
    }

    override fun setLanguage(value: Language) {
        language = value
    }

    override fun getBannerIgnoreId(): Int {
        return bannerIgnoreId
    }

    override fun setBannerIgnoreId(value: Int) {
        bannerIgnoreId = value
    }

    override fun clear() {
        isDarkTheme = true
        language = Language.English
    }
}