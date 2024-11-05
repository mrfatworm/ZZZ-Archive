/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.data

import utils.Language

class FakeSettingRepository : SettingsRepository {
    private var isDarkTheme = true
    private var language = Language.English.project
    private var bannerIgnoreId = 0

    override fun getIsDarkTheme(): Boolean {
        return isDarkTheme
    }

    override fun setIsDarkTheme(value: Boolean) {
        isDarkTheme = value
    }

    override fun getLanguage(): String {
        return language
    }

    override fun setLanguage(langCode: String) {
        language = langCode
    }

    override fun getBannerIgnoreId(): Int {
        return bannerIgnoreId
    }

    override fun setBannerIgnoreId(value: Int) {
        bannerIgnoreId = value
    }

    override fun clear() {
        isDarkTheme = true
        language = Language.English.project
    }
}