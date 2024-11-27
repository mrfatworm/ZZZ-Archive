/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data

import feature.cover_image.data.repository.CoverImageRepositoryImpl
import feature.cover_image.model.stubCoverImageResponse
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class CoverImageRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val repository = CoverImageRepositoryImpl(httpClient)

    @Test
    fun `Get Banner Success`() = runTest {
        val result = repository.getCoverImagesList().getOrNull()
        assertEquals(result, stubCoverImageResponse)
    }

    @Test
    fun `Get Banner Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getCoverImagesList().getOrNull()
        assertNull(result)
    }
}