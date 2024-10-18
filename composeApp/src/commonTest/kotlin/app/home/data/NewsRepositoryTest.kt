/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.stubOfficialNewsDataResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeOfficialWebHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class NewsRepositoryTest {

    private val httpClient = FakeOfficialWebHttp()
    private val repository = NewsRepositoryImpl(httpClient)

    @Test
    fun `Get News Success`() = runTest {
        val result = repository.getNews(0, "") as ZzzResult.Success
        assertEquals(result.data, stubOfficialNewsDataResponse)
    }

    @Test
    fun `Get News Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getNews(0, "") as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }

    @Test
    fun `Get New Every 10 Minutes`() = runTest {
        val result = repository.getNewsPeriodically(10, 0, "").first() as ZzzResult.Success
        assertEquals(result.data, stubOfficialNewsDataResponse)
    }

    @Test
    fun `Get New Every 10 Minutes Fail`() = runTest {
        httpClient.setError(true)
        val result = repository.getNewsPeriodically(10, 0, "").first() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}