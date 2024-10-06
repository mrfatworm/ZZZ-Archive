/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.ImageBannerResponse
import utils.ZzzResult

interface ImageBannerRepository {
    suspend fun getImageBanner(): ZzzResult<ImageBannerResponse>
}