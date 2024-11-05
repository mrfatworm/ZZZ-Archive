/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package mainfunc.data

import mainfunc.model.BannerResponse
import utils.ZzzResult

interface BannerRepository {
    suspend fun getBanner(): ZzzResult<BannerResponse>
}