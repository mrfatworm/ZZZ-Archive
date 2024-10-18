/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.data

import app.bangboo.model.BangbooListResponse
import app.bangboo.model.stubBangbooListResponse
import utils.ZzzResult

class FakeBangbooRepository : BangbooRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBangbooList(): ZzzResult<BangbooListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubBangbooListResponse)
        }
    }
}