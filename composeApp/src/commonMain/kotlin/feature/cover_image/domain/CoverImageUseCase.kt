/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.domain

import feature.cover_image.data.CoverImageRepository

class CoverImageUseCase(private val repository: CoverImageRepository) {
    suspend fun invoke() = repository.getImageBanner()
}