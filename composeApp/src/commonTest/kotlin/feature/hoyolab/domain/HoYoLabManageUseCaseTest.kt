/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabRepository
import feature.setting.data.FakePreferenceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HoYoLabManageUseCaseTest {
    private val hoYoLabRepository = FakeHoYoLabRepository()
    private val zzzCrypto = FakeZzzCrypto()
    private val preferencesRepository = FakePreferenceRepository()
    private val useCase = HoYoLabManageUseCase(hoYoLabRepository, zzzCrypto, preferencesRepository)

    @Test
    fun `Request user game roles and save to database THEN success`() = runTest {
        val result = useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
        assertEquals(result, Result.success(Unit))
        assertEquals(hoYoLabRepository.getAllAccountsFromDB().first().size, 2)
    }

    @Test
    fun `GIVEN Accounts list is empty WHEN Request user game roles and save to database THEN success`() =
        runTest {
            hoYoLabRepository.clearAccountList()
            val result =
                useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
            assertEquals(result, Result.success(Unit))
        }

    @Test
    fun `Request user game roles and save to database THEN fail`() = runTest {
        hoYoLabRepository.setError(true)
        val result = useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
            .getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get all accounts from database`() = runTest {
        val result = useCase.getAllAccountsFromDB().first()
        assertEquals(result, listOf(stubHoYoLabAccountEntity))
    }

    @Test
    fun `Delete account from database`() = runTest {
        useCase.deleteAccountFromDB(123456789)
        assertEquals(hoYoLabRepository.getAllAccountsFromDB().first(), emptyList())
    }

    @Test
    fun `Convert timestamp to local datetime`() {
        val timeZone = TimeZone.of("Asia/Taipei")
        val result = useCase.convertToLocalDatetime(1734058599802, timeZone)
        assertEquals(result, "2024-12-13 10:56")
    }

    @Test
    fun `GIVEN empty account list WHEN Add account THEN set account as default`() = runTest {
        hoYoLabRepository.clearAccountList()
        useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid").getOrNull()
        assertEquals(preferencesRepository.getDefaultHoYoLabAccountUid().first(), 1300051361)
    }

    @Test
    fun `GIVEN 1300051361 is default account WHEN Remove default account THEN set first account as default`() =
        runTest {
            useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
            preferencesRepository.setDefaultHoYoLabAccountUid(1300051361)
            useCase.deleteAccountFromDB(1300051361)
            assertEquals(preferencesRepository.getDefaultHoYoLabAccountUid().first(), 123456789)
        }

    @Test
    fun `GIVEN there is only one account in database and it is default account WHEN Remove default account THEN set default as 0`() =
        runTest {
            preferencesRepository.setDefaultHoYoLabAccountUid(123456789)
            useCase.deleteAccountFromDB(123456789)
            assertEquals(preferencesRepository.getDefaultHoYoLabAccountUid().first(), 0)
        }
}