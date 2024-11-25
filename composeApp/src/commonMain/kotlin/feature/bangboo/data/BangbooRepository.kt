/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse

interface BangbooRepository {
    suspend fun getBangbooList(): Result<BangbooListResponse>
    suspend fun getBangbooDetail(id: Int): Result<BangbooDetailResponse>
}