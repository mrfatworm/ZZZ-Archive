/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover.data

import utils.ZzzResult

class FakeCoverImageRepository : CoverImageRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getImageBanner(): ZzzResult<CoverImageResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubCoverImageResponse)
        }
    }
}