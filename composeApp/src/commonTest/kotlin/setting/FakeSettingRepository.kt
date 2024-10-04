package setting

import utils.Language

class FakeSettingRepository : SettingsRepository {
    private var isDarkTheme = true
    private var language = Language.En

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

    override fun clear() {
        isDarkTheme = true
        language = Language.En
    }

}