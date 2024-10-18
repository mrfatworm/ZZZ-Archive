/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.stubPixivZzzTopic
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakePixivHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class PixivRepositoryTest {

    private val httpClient = FakePixivHttp()
    private val repository = PixivRepositoryImpl(httpClient)

    @Test
    fun `Get News Success`() = runTest {
        val result = repository.getZzzTopic("") as ZzzResult.Success
        assertEquals(result.data, stubPixivZzzTopic)
    }

    @Test
    fun `Get News Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getZzzTopic("") as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }

    @Test
    fun `Get New Every 10 Minutes`() = runTest {
        val result = repository.getZzzTopic("") as ZzzResult.Success
        assertEquals(result.data, stubPixivZzzTopic)
    }

    @Test
    fun `Get New Every 10 Minutes Fail`() = runTest {
        httpClient.setError(true)
        val result = repository.getZzzTopic("") as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}