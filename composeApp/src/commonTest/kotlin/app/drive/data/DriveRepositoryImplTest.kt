/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.data

import app.drive.model.stubDriveListResponse
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test

class DriveRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = DriveRepositoryImpl(httpClient)

    @Test
    fun `Get Drives List Success`() = runTest {
        val result = repository.getDrivesList() as ZzzResult.Success
        assertThat(result.data).isEqualTo(stubDriveListResponse)
    }

    @Test
    fun `Get Drives List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getDrivesList() as ZzzResult.Error
        assertThat(result.exception).isInstanceOf(Exception::class)
    }
}