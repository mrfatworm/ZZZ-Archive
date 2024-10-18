/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.data

import app.bangboo.model.stubBangbooListResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BangbooRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = BangbooRepositoryImpl(httpClient)

    @Test
    fun `Get Bangboo List Success`() = runTest {
        val result = repository.getBangbooList() as ZzzResult.Success
        assertEquals(result.data, stubBangbooListResponse)
    }

    @Test
    fun `Get Bangboo List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooList() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}