/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root.data

import root.model.BannerResponse
import root.model.stubBannerResponse
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