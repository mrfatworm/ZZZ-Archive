/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.data

import app.bangboo.model.BangbooListResponse
import utils.ZzzResult

interface BangbooRepository {
    suspend fun getBangbooList(): ZzzResult<BangbooListResponse>
}