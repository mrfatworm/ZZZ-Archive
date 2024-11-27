/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.repository

import feature.bangboo.model.BangbooDetail
import feature.bangboo.model.BangbooListItem
import kotlinx.coroutines.flow.Flow

interface BangbooRepository {
    suspend fun getBangbooList(): Flow<List<BangbooListItem>>
    suspend fun requestAndUpdateBangbooListDB(): Result<Unit>
    suspend fun getBangbooDetail(id: Int): Result<BangbooDetail>
}