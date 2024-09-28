/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import app.home.model.stubPixivZzzTopic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.ZzzResult

class FakePixivRepository : PixivRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getZzzTopic(): ZzzResult<PixivZzzTopic> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubPixivZzzTopic)
        }
    }

    override fun getZzzTopicPeriodically(
        perMinutes: Int
    ): Flow<ZzzResult<PixivZzzTopic>> {
        return if (isError) {
            flowOf(ZzzResult.Error(Exception()))
        } else {
            flowOf(ZzzResult.Success(stubPixivZzzTopic))
        }
    }
}