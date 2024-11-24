/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.domain

import feature.banner.data.BannerRepository

class BannerUseCase(private val bannerRepository: BannerRepository) {
    suspend fun invoke() = bannerRepository.getBanner()
}