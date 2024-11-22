/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.response.ImageBannerResponse
import utils.ZzzResult

interface ImageBannerRepository {
    suspend fun getImageBanner(): ZzzResult<ImageBannerResponse>
}