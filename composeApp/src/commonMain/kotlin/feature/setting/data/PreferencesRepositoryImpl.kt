/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    PreferencesRepository {

    companion object {
        const val DEFAULT_IS_DARK_THEME = true
        const val DEFAULT_LANGUAGE = ""
        const val DEFAULT_UI_SCALE = 1f
        const val DEFAULT_FONT_SCALE = 1f
        const val DEFAULT_HOYOLAB_ACCOUNT_UID = 0
    }

    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")
    private val languageKey = stringPreferencesKey("language")
    private val uiScaleKey = floatPreferencesKey("ui_scale")
    private val fontScaleKey = floatPreferencesKey("font_scale")
    private val defaultHoYoLabAccountUidKey = intPreferencesKey("default_hoyolab_account_uid")


    override fun getIsDarkTheme(): Flow<Boolean> = dataStore.data.map {
        it[isDarkThemeKey] ?: DEFAULT_IS_DARK_THEME
    }

    override suspend fun setIsDarkTheme(value: Boolean) {
        dataStore.edit { it[isDarkThemeKey] = value }
    }

    override fun getLanguageCode(): Flow<String> = dataStore.data.map {
        it[languageKey] ?: DEFAULT_LANGUAGE
    }

    override suspend fun setLanguage(langCode: String) {
        dataStore.edit { it[languageKey] = langCode }
    }

    override fun getUiScale(): Flow<Float> = dataStore.data.map {
        it[uiScaleKey]?.toFloat() ?: DEFAULT_UI_SCALE
    }

    override suspend fun setUiScale(value: Float) {
        dataStore.edit { it[uiScaleKey] = value }
    }

    override fun getFontScale(): Flow<Float> = dataStore.data.map {
        it[fontScaleKey]?.toFloat() ?: DEFAULT_FONT_SCALE
    }

    override suspend fun setFontScale(value: Float) {
        dataStore.edit { it[fontScaleKey] = value }
    }

    override fun getDefaultHoYoLabAccountUid(): Flow<Int> = dataStore.data.map {
        it[defaultHoYoLabAccountUidKey]?.toInt() ?: DEFAULT_HOYOLAB_ACCOUNT_UID
    }

    override suspend fun setDefaultHoYoLabAccountUid(value: Int) {
        dataStore.edit { it[defaultHoYoLabAccountUidKey] = value }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

