/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.data

import app.wengine.model.stubWEnginesListResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WEngineRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = WEngineRepositoryImpl(httpClient)

    @Test
    fun `Get W-Engines List Success`() = runTest {
        val result = repository.getWEnginesList() as ZzzResult.Success
        assertEquals(result.data, stubWEnginesListResponse)
    }

    @Test
    fun `Get W-Engines List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getWEnginesList() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}