/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.database.FakeHoYoLabAccountDao
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.model.stubPlayerBasicInfo
import feature.hoyolab.model.stubPlayerDetailResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import network.FakeHoYoLabHttp

class HoYoLabConfigRepositoryTest {
    private val dao = FakeHoYoLabAccountDao()
    private val httpClient = FakeHoYoLabHttp()
    private val repository = HoYoLabConfigRepositoryImpl(httpClient, dao)

    @Test
    fun `Request user game roles by LToken THEN success`() = runTest {
        val result =
            repository.requestUserGameRolesByLToken(
                region = "prod_gf_jp",
                lToken = "fake_ltoken",
                ltUid = "fake_lt_uid"
            )
        assertEquals(Result.success(listOf(stubPlayerBasicInfo)), result)
    }

    @Test
    fun `Request user game roles by LToken THEN failed`() = runTest {
        httpClient.setError(true)
        val result =
            repository
                .requestUserGameRolesByLToken(
                    region = "prod_gf_jp",
                    lToken = "fake_ltoken",
                    ltUid = "fake_lt_uid"
                )
                .getOrNull()
        assertNull(result)
    }

    @Test
    fun `Request player detail THEN success`() = runTest {
        val result =
            repository.requestPlayerDetail(
                uid = 1300051361,
                region = "prod_gf_jp",
                lToken = "fake_ltoken",
                ltUid = "fake_lt_uid"
            )
        assertEquals(Result.success(stubPlayerDetailResponse), result)
    }

    @Test
    fun `Request player detail THEN failed`() = runTest {
        httpClient.setError(true)
        val result =
            repository
                .requestPlayerDetail(
                    uid = 1300051361,
                    region = "prod_gf_jp",
                    lToken = "fake_ltoken",
                    ltUid = "fake_lt_uid"
                ).getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get all accounts from database`() = runTest {
        val result = dao.getAccountList().first()
        assertEquals(listOf(stubHoYoLabAccountEntity), result)
    }

    @Test
    fun `Get account from database`() = runTest {
        val result = dao.getAccount(123456789).first()
        assertEquals(stubHoYoLabAccountEntity, result)
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
        assertEquals(listOf(stubHoYoLabAccountEntity, stubHoYoLabAccountEntity), result.first())
    }

    @Test
    fun `Clear the legacy credential columns`() = runTest {
        repository.clearLegacyCredentialsInDB(123456789)
        val account = dao.getAccount(123456789).first()
        assertEquals(0, account?.lToken?.size)
        assertEquals(0, account?.ltUid?.size)
    }

    @Test
    fun `Delete account from database`() = runTest {
        dao.deleteAccount(123456789)
        val result = dao.getAccountList().firstOrNull()
        assertEquals(emptyList(), result)
    }

    @Test
    fun `Delete all accounts from database`() = runTest {
        dao.deleteAccountList()
        val result = dao.getAccountList().firstOrNull()
        assertEquals(emptyList(), result)
    }
}
