/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.cover.data

import utils.ZzzResult

interface CoverImageRepository {
    suspend fun getImageBanner(): ZzzResult<CoverImageResponse>
}