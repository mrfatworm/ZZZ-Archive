/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data

import feature.cover_image.data.database.FakeCoverImagesListDao
import feature.cover_image.data.repository.CoverImageRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals


class CoverImageRepositoryTest {

    private val httpClient = FakeZzzHttp()
    private val database = FakeCoverImagesListDao()
    private val repository = CoverImageRepositoryImpl(httpClient, database)
    // Remote: 2 Cover Images, Local: 1 Cover Image

    @Test
    fun `WHEN Get cover images list success THEN return local DB`() = runTest {
        val result = repository.getCoverImagesList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `WHEN Request cover images list success THEN return updated DB`() = runTest {
        repository.requestAndUpdateCoverImagesListDB()
        val result = repository.getCoverImagesList().first()
        assertEquals(result.size, 2)
    }

    @Test
    fun `GIVEN cover images list DB is empty WHEN Get cover images list THEN Auto request and return updated DB`() =
        runTest {
            database.deleteCoverImagesList()
            val result = repository.getCoverImagesList().first()
            assertEquals(result.size, 2)
        }

    @Test
    fun `WHEN Request cover images list error THEN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getCoverImagesList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `GIVEN cover images list DB is empty WHEN Request cover images list error THEN return empty DB`() =
        runTest {
        httpClient.setError(true)
            database.deleteCoverImagesList()
            val result = repository.getCoverImagesList().first()
            assertEquals(result.size, 0)
    }
}