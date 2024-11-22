/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.home.model.response.PixivTopicResponse


interface PixivHttp {
    val timeout: Long
    suspend fun requestZzzTopic(zzzTag: String): PixivTopicResponse
}