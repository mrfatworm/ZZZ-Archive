/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.news.data.OfficialNewsResponse


interface OfficialWebHttp {
    suspend fun requestNews(amount: Int, languagePath: String): OfficialNewsResponse
}