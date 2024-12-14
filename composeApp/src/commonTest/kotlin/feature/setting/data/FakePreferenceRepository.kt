/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Language

class FakePreferenceRepository : PreferencesRepository {
    private var isDarkTheme = true
    private var language = Language.English.code
    private var uiScale = 1f
    private var fontScale = 1f
    private var defaultHoYoLabAccountUid = 0

    override fun getIsDarkTheme(): Flow<Boolean> = flow {
        emit(isDarkTheme)
    }

    override suspend fun setIsDarkTheme(value: Boolean) {
        isDarkTheme = value
    }

    override fun getLanguageCode(): Flow<String> = flow {
        emit(language)
    }

    override suspend fun setLanguage(langCode: String) {
        language = langCode
    }

    override fun getUiScale(): Flow<Float> = flow {
        emit(uiScale)
    }

    override suspend fun setUiScale(value: Float) {
        uiScale = value
    }

    override fun getFontScale(): Flow<Float> = flow {
        emit(fontScale)
    }

    override suspend fun setFontScale(value: Float) {
        fontScale = value
    }

    override fun getDefaultHoYoLabAccountUid(): Flow<Int> = flow {
        emit(defaultHoYoLabAccountUid)
    }

    override suspend fun setDefaultHoYoLabAccountUid(value: Int) {
        defaultHoYoLabAccountUid = value
    }

    override suspend fun clear() {
        isDarkTheme = true
        language = Language.English.code
    }
}