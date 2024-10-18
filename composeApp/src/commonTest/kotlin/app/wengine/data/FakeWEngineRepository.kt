/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.data

import app.wengine.model.WEnginesListResponse
import app.wengine.model.stubWEnginesListResponse
import utils.ZzzResult

class FakeWEngineRepository : WEngineRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubWEnginesListResponse)
        }
    }
}