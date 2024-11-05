/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.data

import app.home.model.PixivZzzTopic
import utils.ZzzResult

interface PixivRepository {
    suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivZzzTopic>
}