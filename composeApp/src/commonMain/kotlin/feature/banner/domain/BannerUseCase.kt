/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.BannerRepository
import feature.setting.data.SettingsRepository

class BannerUseCase(
    private val bannerRepository: BannerRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend fun invoke() = bannerRepository.getBanner()

    fun getBannerIgnoreId() = settingsRepository.getBannerIgnoreId()

    fun setBannerIgnoreId(id: Int) = settingsRepository.setBannerIgnoreId(id)

}