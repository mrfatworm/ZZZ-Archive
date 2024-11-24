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
    fun `Get Pixiv Topic Success`() = runTest {
        val result = repository.getZzzTopic("").getOrNull()
        assertEquals(result, stubPixivTopicResponse)
    }

    @Test
    fun `Get Pixiv Topic Fail`() = runTest {
        httpClient.setError(true)
        val result = repository.getZzzTopic("").getOrNull()
        assertNull(result)
    }
}