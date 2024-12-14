/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.data

class FakeBannerRepository : BannerRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBanner(languageCode: String): Result<BannerResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBannerResponse)
        }
    }
}