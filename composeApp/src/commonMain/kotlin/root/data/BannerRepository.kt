/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root.data

import root.model.BannerResponse
import utils.ZzzResult

interface BannerRepository {
    suspend fun getBanner(): ZzzResult<BannerResponse>
}