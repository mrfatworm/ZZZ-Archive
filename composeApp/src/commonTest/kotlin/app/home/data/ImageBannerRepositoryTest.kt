/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.stubImageBannerResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test


class ImageBannerRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = ImageBannerRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getImageBanner() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubImageBannerResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getImageBanner() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}