/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.data

import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BannerRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = BannerRepositoryImpl(httpClient)

    @Test
    fun `Get banner success`() = runTest {
        val result = repository.getBanner("").getOrNull()
        assertEquals(stubBannerResponse, result)
    }

    @Test
    fun `Get banner error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBanner("").getOrNull()
        assertNull(result)
    }
}