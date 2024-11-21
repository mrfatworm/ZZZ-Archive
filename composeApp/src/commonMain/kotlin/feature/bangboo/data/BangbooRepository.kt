/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import utils.ZzzResult

interface BangbooRepository {
    suspend fun getBangbooList(): ZzzResult<BangbooListResponse>
    suspend fun getBangbooDetail(id: Int): ZzzResult<BangbooDetailResponse>
}