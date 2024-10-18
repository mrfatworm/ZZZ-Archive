/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.data

import app.wengine.model.stubWEnginesListResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test

class WEngineRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = WEngineRepositoryImpl(httpClient)

    @Test
    fun `Get W-Engines List Success`() = runTest {
        val result = repository.getWEnginesList() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubWEnginesListResponse)
    }

    @Test
    fun `Get W-Engines List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getWEnginesList() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}