/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root.data

import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import root.model.stubBannerResponse
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class BannerRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = BannerRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getBanner() as ZzzResult.Success
        assertEquals(result.data, stubBannerResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBanner() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}