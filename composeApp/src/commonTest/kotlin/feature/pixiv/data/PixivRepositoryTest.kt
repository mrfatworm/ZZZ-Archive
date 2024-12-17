/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.pixiv.data

import kotlinx.coroutines.test.runTest
import network.FakePixivHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class PixivRepositoryTest {

    private val httpClient = FakePixivHttp()
    private val repository = PixivRepositoryImpl(httpClient)

    @Test
    fun `Get Pixiv topic success`() = runTest {
        val result = repository.updateZzzTopic("").getOrNull()
        assertEquals(stubPixivTopicResponse, result)
    }

    @Test
    fun `Get Pixiv topic error`() = runTest {
        httpClient.setError(true)
        val result = repository.updateZzzTopic("").getOrNull()
        assertNull(result)
    }
}