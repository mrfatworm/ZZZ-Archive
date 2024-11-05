/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.home.data

import app.home.model.stubImageBannerResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ImageBannerRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = ImageBannerRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getImageBanner() as ZzzResult.Success
        assertEquals(result.data, stubImageBannerResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getImageBanner() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}