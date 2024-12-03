/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import feature.setting.data.PreferencesRepository

class UiScaleUseCase(private val repository: PreferencesRepository) {
    fun getUiScale(): Float = repository.getUiScale()
    fun setUiScale(value: Float) = repository.setUiScale(value)
    fun getFontScale(): Float = repository.getFontScale()
    fun setFontScale(value: Float) = repository.setFontScale(value)
}