/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.data

import app.bangboo.model.stubBangbooListResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test

class BangbooRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = BangbooRepositoryImpl(httpClient)

    @Test
    fun `Get Bangboo List Success`() = runTest {
        val result = repository.getBangbooList() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubBangbooListResponse)
    }

    @Test
    fun `Get Bangboo List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooList() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}