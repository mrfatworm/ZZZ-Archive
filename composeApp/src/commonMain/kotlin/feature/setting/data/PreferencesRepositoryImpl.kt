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

class PreferencesRepositoryImpl(private val dataStore: DataStore<Preferences>) : PreferencesRepository {
    companion object {
        const val DEFAULT_IS_DARK_THEME = true
        const val DEFAULT_LANGUAGE = ""
        const val DEFAULT_UI_SCALE = 1f
        const val DEFAULT_FONT_SCALE = 1f
        const val DEFAULT_HOYOLAB_ACCOUNT_UID = 0
        const val DEFAULT_HOYOLAB_CREDENTIALS_MIGRATED = false
    }

    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")
    private val languageKey = stringPreferencesKey("language")
    private val uiScaleKey = floatPreferencesKey("ui_scale")
    private val fontScaleKey = floatPreferencesKey("font_scale")
    private val defaultHoYoLabAccountUidKey = intPreferencesKey("default_hoyolab_account_uid")
    private val hoYoLabCredentialsMigratedKey = booleanPreferencesKey("hoyolab_credentials_migrated")

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
        it[uiScaleKey] ?: DEFAULT_UI_SCALE
    }

    override suspend fun setUiScale(value: Float) {
        dataStore.edit { it[uiScaleKey] = value }
    }

    override fun getFontScale(): Flow<Float> = dataStore.data.map {
        it[fontScaleKey] ?: DEFAULT_FONT_SCALE
    }

    override suspend fun setFontScale(value: Float) {
        dataStore.edit { it[fontScaleKey] = value }
    }

    override fun getDefaultHoYoLabAccountUid(): Flow<Int> = dataStore.data.map {
        it[defaultHoYoLabAccountUidKey] ?: DEFAULT_HOYOLAB_ACCOUNT_UID
    }

    override suspend fun setDefaultHoYoLabAccountUid(value: Int) {
        dataStore.edit { it[defaultHoYoLabAccountUidKey] = value }
    }

    override fun getHoYoLabCredentialsMigrated(): Flow<Boolean> = dataStore.data.map {
        it[hoYoLabCredentialsMigratedKey] ?: DEFAULT_HOYOLAB_CREDENTIALS_MIGRATED
    }

    override suspend fun setHoYoLabCredentialsMigrated(value: Boolean) {
        dataStore.edit { it[hoYoLabCredentialsMigratedKey] = value }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}
