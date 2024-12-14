/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.setting.data.PreferencesRepository

class HoYoLabSettingUseCase(private val preferencesRepository: PreferencesRepository) {

    fun getDefaultHoYoLabAccountUid(): Int {
        return preferencesRepository.getDefaultHoYoLabAccountUid()
    }

    fun setDefaultHoYoLabAccountUid(uid: Int) {
        preferencesRepository.setDefaultHoYoLabAccountUid(uid)
    }

}