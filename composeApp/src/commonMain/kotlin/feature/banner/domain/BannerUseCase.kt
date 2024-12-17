/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.BannerRepository
import feature.banner.data.BannerResponse
import feature.setting.data.SystemConfigRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.first

class BannerUseCase(
    private val bannerRepository: BannerRepository,
    private val systemConfigRepository: SystemConfigRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke(): Result<BannerResponse> {
        val bannerResponse =
            bannerRepository.getBanner(languageUseCase.getLanguage().first().officialCode)
                .getOrNull()
        if (getBannerIgnoreId().first() < (bannerResponse?.id ?: 0)) {
            return Result.success(bannerResponse!!)
        } else {
            return Result.failure(Exception("No new banner"))
        }
    }


    private fun getBannerIgnoreId() = systemConfigRepository.getBannerIgnoreId()

    suspend fun setBannerIgnoreId(id: Int) = systemConfigRepository.setBannerIgnoreId(id)

}