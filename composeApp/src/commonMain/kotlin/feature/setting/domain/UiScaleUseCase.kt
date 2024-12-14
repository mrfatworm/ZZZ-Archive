/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class UiScaleUseCase(private val repository: PreferencesRepository) {
    fun getUiScale(): Flow<Float> = repository.getUiScale()
    suspend fun setUiScale(value: Float) = repository.setUiScale(value)
    fun getFontScale(): Flow<Float> = repository.getFontScale()
    suspend fun setFontScale(value: Float) = repository.setFontScale(value)
}