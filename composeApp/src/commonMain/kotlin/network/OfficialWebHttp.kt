/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.home.model.OfficialNewsResponse


interface OfficialWebHttp {
    val timeout: Long
    suspend fun requestNews(amount: Int, langKey: String): OfficialNewsResponse
}