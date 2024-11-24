/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.BangbooRepository

class BangbooDetailUseCase(private val bangbooRepository: BangbooRepository) {
    suspend operator fun invoke(id: Int) = bangbooRepository.getBangbooDetail(id)
}