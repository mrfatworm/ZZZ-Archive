/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data

class FakeCoverImageRepository : CoverImageRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getImageBanner(): Result<CoverImageResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubCoverImageResponse)
        }
    }
}