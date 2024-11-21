/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.PixivZzzTopic
import utils.ZzzResult

interface PixivRepository {
    suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivZzzTopic>
}