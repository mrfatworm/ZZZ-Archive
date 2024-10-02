/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.PixivZzzTopic
import utils.ZzzResult

interface PixivRepository {
    suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivZzzTopic>
}