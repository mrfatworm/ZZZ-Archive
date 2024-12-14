/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import androidx.compose.ui.text.intl.Locale
import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import utils.Language
import utils.changePlatformLanguage

interface LanguageUseCase {
    fun getLanguage(): Flow<Language>
    suspend fun setLanguage(langCode: String)
}

class LanguageUseCaseImpl(private val preferencesRepository: PreferencesRepository) :
    LanguageUseCase {

    override fun getLanguage(): Flow<Language> = flow {
        val langCode = preferencesRepository.getLanguageCode().first()
        val deviceLanguage: String = Locale.current.language
        val language =
            if (langCode == "") Language.entries.firstOrNull { it.code == deviceLanguage }
                ?: Language.English
            else Language.entries.firstOrNull { it.code == langCode } ?: Language.English
        emit(language)
    }

    override suspend fun setLanguage(langCode: String) {
        preferencesRepository.setLanguage(langCode)
        changePlatformLanguage(langCode)
    }
}