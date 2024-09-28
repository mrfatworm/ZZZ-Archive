/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import kotlinx.coroutines.flow.Flow
import utils.ZzzResult

interface PixivRepository {
    suspend fun getZzzTopic(): ZzzResult<PixivZzzTopic>
    fun getZzzTopicPeriodically(perMinutes: Int): Flow<ZzzResult<PixivZzzTopic>>
}