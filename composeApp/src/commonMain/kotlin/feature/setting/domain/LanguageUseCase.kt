/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import androidx.compose.ui.text.intl.Locale
import feature.setting.data.PreferencesRepository
import utils.Language
import utils.changePlatformLanguage

interface LanguageUseCase {
    fun getLanguage(): Language
    fun setLanguage(langCode: String)
}

class LanguageUseCaseImpl(private val preferencesRepository: PreferencesRepository) :
    LanguageUseCase {

    override fun getLanguage(): Language {
        val langCode = preferencesRepository.getLanguageCode()
        val deviceLanguage: String = Locale.current.language
        val language =
            if (langCode == "") Language.entries.firstOrNull { it.code == deviceLanguage }
                ?: Language.English
            else Language.entries.firstOrNull { it.code == langCode } ?: Language.English
        return language
    }

    override fun setLanguage(langCode: String) {
        preferencesRepository.setLanguage(langCode)
        changePlatformLanguage(langCode)
    }
}