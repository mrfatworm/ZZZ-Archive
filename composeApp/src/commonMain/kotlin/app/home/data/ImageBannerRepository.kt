/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.data

import app.home.model.ImageBannerResponse
import utils.ZzzResult

interface ImageBannerRepository {
    suspend fun getImageBanner(): ZzzResult<ImageBannerResponse>
}