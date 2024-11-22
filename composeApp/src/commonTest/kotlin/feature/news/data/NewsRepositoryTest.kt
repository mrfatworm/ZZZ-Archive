/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.news.data

import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeOfficialWebHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class NewsRepositoryTest {

    private val httpClient = FakeOfficialWebHttp()
    private val repository = OfficialNewsRepositoryImpl(httpClient)

    @Test
    fun `Get News Success`() = runTest {
        val result = repository.getNews(0) as ZzzResult.Success
        assertEquals(result.data, stubOfficialNewsDataResponse.data.list)
    }

    @Test
    fun `Get News Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getNews(0) as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}