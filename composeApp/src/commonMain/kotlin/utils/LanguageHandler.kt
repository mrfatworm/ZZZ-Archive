/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import androidx.compose.ui.text.intl.Locale
import feature.setting.data.SettingsRepository

interface LanguageHandler {
    fun getLanguage(): Language
}

class LanguageHandlerImpl(private val settingsRepository: SettingsRepository) : LanguageHandler {

    override fun getLanguage(): Language {
        val langCode = settingsRepository.getLanguage()
        val defaultLanguage: String = Locale.current.language
        val language =
            if (langCode == "") Language.entries.firstOrNull { it.project == defaultLanguage }
                ?: Language.English
            else Language.entries.firstOrNull { it.project == langCode } ?: Language.English
        return language
    }
}