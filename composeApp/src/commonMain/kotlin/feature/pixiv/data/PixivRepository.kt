/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.pixiv.data

import feature.home.model.response.PixivTopicResponse
import utils.ZzzResult

interface PixivRepository {
    suspend fun getZzzTopic(zzzTag: String): ZzzResult<PixivTopicResponse>
}