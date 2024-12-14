/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import utils.Language

class FakePreferenceRepository : PreferencesRepository {
    private var isDarkTheme = true
    private var language = Language.English.code
    private var uiScale = 1f
    private var fontScale = 1f
    private var defaultHoYoLabAccountUid = 0

    override fun getIsDarkTheme(): Boolean {
        return isDarkTheme
    }

    override fun setIsDarkTheme(value: Boolean) {
        isDarkTheme = value
    }

    override fun getLanguageCode(): String {
        return language
    }

    override fun setLanguage(langCode: String) {
        language = langCode
    }

    override fun getUiScale(): Float {
        return uiScale
    }

    override fun setUiScale(value: Float) {
        uiScale = value
    }

    override fun getFontScale(): Float {
        return fontScale
    }

    override fun setFontScale(value: Float) {
        fontScale = value
    }

    override fun getDefaultHoYoLabAccountUid(): Int {
        return defaultHoYoLabAccountUid
    }

    override fun setDefaultHoYoLabAccountUid(value: Int) {
        defaultHoYoLabAccountUid = value
    }

    override fun clear() {
        isDarkTheme = true
        language = Language.English.code
    }
}