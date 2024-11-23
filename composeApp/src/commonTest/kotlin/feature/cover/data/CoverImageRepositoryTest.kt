/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover.data

import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class CoverImageRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = CoverImageRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getImageBanner() as ZzzResult.Success
        assertEquals(result.data, stubCoverImageResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getImageBanner() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}