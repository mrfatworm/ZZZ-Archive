/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.BannerRepository
import feature.setting.data.SystemConfigRepository

class BannerUseCase(
    private val bannerRepository: BannerRepository,
    private val systemConfigRepository: SystemConfigRepository
) {
    suspend fun invoke() = bannerRepository.getBanner()

    fun getBannerIgnoreId() = systemConfigRepository.getBannerIgnoreId()

    fun setBannerIgnoreId(id: Int) = systemConfigRepository.setBannerIgnoreId(id)

}