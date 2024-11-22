/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.response.ImageBannerResponse
import feature.home.model.response.stubImageBannerResponse
import utils.ZzzResult

class FakeImageBannerRepository : ImageBannerRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getImageBanner(): ZzzResult<ImageBannerResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubImageBannerResponse)
        }
    }
}