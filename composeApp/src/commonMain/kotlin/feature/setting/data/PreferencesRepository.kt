/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getIsDarkTheme(): Flow<Boolean>
    suspend fun setIsDarkTheme(value: Boolean)
    fun getLanguageCode(): Flow<String>
    suspend fun setLanguage(langCode: String)
    fun getUiScale(): Flow<Float>
    suspend fun setUiScale(value: Float)
    fun getFontScale(): Flow<Float>
    suspend fun setFontScale(value: Float)
    fun getDefaultHoYoLabAccountUid(): Flow<Int>
    suspend fun setDefaultHoYoLabAccountUid(value: Int)
    suspend fun clear()
}