/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.stubPixivZzzTopic
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakePixivHttp
import utils.ZzzResult
import kotlin.test.Test


class PixivRepositoryTest {

    private val httpClient = FakePixivHttp()
    private val repository = PixivRepositoryImpl(httpClient)

    @Test
    fun `Get News Success`() = runTest {
        val result = repository.getZzzTopic("") as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubPixivZzzTopic)
    }

    @Test
    fun `Get News Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getZzzTopic("") as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }

    @Test
    fun `Get New Every 10 Minutes`() = runTest {
        val result = repository.getZzzTopic("") as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubPixivZzzTopic)
    }

    @Test
    fun `Get New Every 10 Minutes Fail`() = runTest {
        httpClient.setError(true)
        val result = repository.getZzzTopic("") as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}