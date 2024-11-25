/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.data

interface BannerRepository {
    suspend fun getBanner(): Result<BannerResponse>
}