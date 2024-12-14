/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class HoYoLabPreferenceUseCase(private val preferencesRepository: PreferencesRepository) {

    fun getDefaultHoYoLabAccountUid(): Flow<Int> =
        preferencesRepository.getDefaultHoYoLabAccountUid()

    suspend fun setDefaultHoYoLabAccountUid(uid: Int) {
        preferencesRepository.setDefaultHoYoLabAccountUid(uid)
    }

}