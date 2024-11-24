/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.cover_image.data

interface CoverImageRepository {
    suspend fun getImageBanner(): Result<CoverImageResponse>
}