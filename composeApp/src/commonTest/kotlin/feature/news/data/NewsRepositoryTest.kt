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
    fun `Get News Success`() = runTest {
        val result = repository.getNews(0).getOrNull()
        assertEquals(result, stubOfficialNewsDataResponse.data.list)
    }

    @Test
    fun `Get News Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getNews(0).getOrNull()
        assertNull(result)
    }
}