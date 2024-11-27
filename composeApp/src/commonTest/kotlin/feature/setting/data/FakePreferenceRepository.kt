/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import utils.Language

class FakePreferenceRepository : PreferencesRepository {
    private var isDarkTheme = true
    private var language = Language.English.code

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

    override fun clear() {
        isDarkTheme = true
        language = Language.English.code
    }
}