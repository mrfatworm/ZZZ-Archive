/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.ImageBannerResponse
import app.home.model.stubImageBannerResponse
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