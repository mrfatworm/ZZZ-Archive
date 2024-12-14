/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.model.stubPlayerBasicInfo
import feature.hoyolab.model.stubPlayerDetailResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import network.FakeHoYoLabHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HoYoLabRepositoryTest {
    private val dao = FakeHoYoLabAccountDao()
    private val httpClient = FakeHoYoLabHttp()
    private val repository = HoYoLabRepositoryImpl(httpClient, dao)

    @Test
    fun `Request user game roles by LToken THEN success`() = runTest {
        val result =
            repository.requestUserGameRolesByLToken("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
        assertEquals(result, Result.success(listOf(stubPlayerBasicInfo)))
    }

    @Test
    fun `Request user game roles by LToken THEN failed`() = runTest {
        httpClient.setError(true)
        val result =
            repository.requestUserGameRolesByLToken("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
                .getOrNull()
        assertNull(result)
    }

    @Test
    fun `Request player detail THEN success`() = runTest {
        val result = repository.requestPlayerDetail(
            1300051361, "prod_gf_jp", "fake_ltoken", "fake_lt_uid"
        )
        assertEquals(result, Result.success(stubPlayerDetailResponse))
    }

    @Test
    fun `Request player detail THEN failed`() = runTest {
        httpClient.setError(true)
        val result = repository.requestPlayerDetail(
            1300051361, "prod_gf_jp", "fake_ltoken", "fake_lt_uid"
        ).getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get all accounts from database`() = runTest {
        val result = dao.getAccountList().first()
        assertEquals(result, listOf(stubHoYoLabAccountEntity))
    }

    @Test
    fun `Get account from database`() = runTest {
        val result = dao.getAccount(123456789).first()
        assertEquals(result, stubHoYoLabAccountEntity)
    }

    @Test
    fun `Get not exist account from database`() = runTest {
        val result = dao.getAccount(1300051361).firstOrNull()
        assertNull(result)
    }

    @Test
    fun `Add account to database`() = runTest {
        dao.insertAccount(stubHoYoLabAccountEntity)
        val result = dao.getAccountList()
        assertEquals(result.first(), listOf(stubHoYoLabAccountEntity, stubHoYoLabAccountEntity))
    }

    @Test
    fun `Delete account from database`() = runTest {
        dao.deleteAccount(123456789)
        val result = dao.getAccountList().firstOrNull()
        assertEquals(result, emptyList())
    }

    @Test
    fun `Delete all accounts from database`() = runTest {
        dao.deleteAccountList()
        val result = dao.getAccountList().firstOrNull()
        assertEquals(result, emptyList())
    }
}