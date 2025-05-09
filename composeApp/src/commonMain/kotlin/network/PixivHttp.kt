/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.pixiv.data.PixivTopicResponse


interface PixivHttp {
    suspend fun requestZzzTopic(zzzTag: String): PixivTopicResponse
}