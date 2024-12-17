/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import kotlinx.coroutines.test.runTest
import network.FakeOfficialWebHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class NewsRepositoryTest {

    private val httpClient = FakeOfficialWebHttp()
    private val repository = OfficialNewsRepositoryImpl(httpClient)

    @Test
    fun `Get news success`() = runTest {
        val result = repository.getNews(0, "").getOrNull()
        assertEquals(stubOfficialNewsDataResponse.data.list, result)
    }

    @Test
    fun `Get news error`() = runTest {
        httpClient.setError(true)
        val result = repository.getNews(0, "").getOrNull()
        assertNull(result)
    }
}