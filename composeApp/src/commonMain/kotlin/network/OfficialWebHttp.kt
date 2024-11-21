/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.home.model.OfficialNewsResponse


interface OfficialWebHttp {
    val timeout: Long
    val languagePath: String
    suspend fun requestNews(amount: Int): OfficialNewsResponse
}