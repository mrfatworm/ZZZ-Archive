/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.stubBannerResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttpClient
import utils.ZzzResult
import kotlin.test.Test


class BannerRepositoryTest {

    private val httpClient = FakeZzzHttpClient()
    private val repository = BannerRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getBanner() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubBannerResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBanner() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}