/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.BannerResponse
import app.home.model.stubBannerResponse
import utils.ZzzResult

class FakeBannerRepository : BannerRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBanner(): ZzzResult<BannerResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubBannerResponse)
        }
    }
}