/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.stubBangbooListResponse
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

    @Test
    fun `Get Bangboo Detail Success`() = runTest {
        val result = repository.getBangbooDetail(6) as ZzzResult.Success
        assertEquals(result.data.id, 6)
    }

    @Test
    fun `Get Bangboo Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooDetail(6) as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))

    }
}