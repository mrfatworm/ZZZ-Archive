/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.credential.FakeHoYoLabCredentialStore
import feature.hoyolab.data.credential.HoYoLabCredential
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabConfigRepository
import feature.setting.data.FakePreferenceRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.TimeZone

class HoYoLabManageUseCaseTest {
    private val hoYoLabRepository = FakeHoYoLabConfigRepository()
    private val credentialStore = FakeHoYoLabCredentialStore()
    private val preferencesRepository = FakePreferenceRepository()
    private val useCase = HoYoLabManageUseCase(hoYoLabRepository, credentialStore, preferencesRepository)

    @Test
    fun `Request user game roles and save to database THEN success`() = runTest {
        val result = useCase.requestUserInfoAndSave(
            region = "prod_gf_jp",
            lToken = "fake_ltoken",
            ltUid = "fake_lt_uid"
        )
        assertEquals(Result.success(Unit), result)
        assertEquals(2, hoYoLabRepository.getAllAccountsFromDB().first().size)
    }

    @Test
    fun `GIVEN Accounts list is empty WHEN Request user game roles and save to database THEN success`() = runTest {
        hoYoLabRepository.clearAccountList()
        val result = useCase.requestUserInfoAndSave(
            region = "prod_gf_jp",
            lToken = "fake_ltoken",
            ltUid = "fake_lt_uid"
        )
        assertEquals(Result.success(Unit), result)
    }

    @Test
    fun `Request user game roles and save to database THEN fail`() = runTest {
        hoYoLabRepository.setError(true)
        val result =
            useCase.requestUserInfoAndSave(
                region = "prod_gf_jp",
                lToken = "fake_ltoken",
                ltUid = "fake_lt_uid"
            ).getOrNull()
        assertNull(result)
    }

    @Test
    fun `Request user game roles and save THEN the credential goes to the credential store`() = runTest {
        useCase.requestUserInfoAndSave(region = "prod_gf_jp", lToken = "fake_ltoken", ltUid = "fake_lt_uid")
        assertEquals(
            HoYoLabCredential(lToken = "fake_ltoken", ltUid = "fake_lt_uid"),
            credentialStore.read(1300051361)
        )
    }

    @Test
    fun `Request user game roles and save THEN no credential reaches the database`() = runTest {
        useCase.requestUserInfoAndSave(region = "prod_gf_jp", lToken = "fake_ltoken", ltUid = "fake_lt_uid")
        val account = hoYoLabRepository.getAllAccountsFromDB().first().first { it.uid == 1300051361 }
        assertEquals(0, account.lToken.size)
        assertEquals(0, account.ltUid.size)
    }

    @Test
    fun `Re-sync an account whose credential is gone THEN fail`() = runTest {
        credentialStore.delete(123456789)
        val result = useCase.reSyncAccount(123456789)
        assertNull(result.getOrNull())
    }

    @Test
    fun `Delete account THEN its credential is deleted too`() = runTest {
        useCase.deleteAccountFromDB(123456789)
        assertNull(credentialStore.read(123456789))
    }

    @Test
    fun `Get all accounts from database`() = runTest {
        val result = useCase.getAllAccountsFromDB().first()
        assertEquals(listOf(stubHoYoLabAccountEntity), result)
    }

    @Test
    fun `Delete account from database`() = runTest {
        useCase.deleteAccountFromDB(123456789)
        val result = hoYoLabRepository.getAllAccountsFromDB().first()
        assertEquals(emptyList(), result)
    }

    @Test
    fun `Convert timestamp to local datetime`() {
        val timeZone = TimeZone.of("Asia/Taipei")
        val result = useCase.convertToLocalDatetime(timestamp = 1734058599802, timeZone = timeZone)
        assertEquals("2024-12-13 10:56", result)
    }

    @Test
    fun `GIVEN empty account list WHEN Add account THEN set account as default`() = runTest {
        hoYoLabRepository.clearAccountList()
        useCase.requestUserInfoAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid").getOrNull()
        val result = preferencesRepository.getDefaultHoYoLabAccountUid().first()
        assertEquals(1300051361, result)
    }

    @Test
    fun `GIVEN 1300051361 is default account WHEN Remove default account THEN set first account as default`() =
        runTest {
            useCase.requestUserInfoAndSave(region = "prod_gf_jp", lToken = "fake_ltoken", ltUid = "fake_lt_uid")
            preferencesRepository.setDefaultHoYoLabAccountUid(1300051361)
            useCase.deleteAccountFromDB(1300051361)
            val result = preferencesRepository.getDefaultHoYoLabAccountUid().first()
            assertEquals(123456789, result)
        }

    @Test
    fun `GIVEN there is only one account in database and it is default account WHEN Remove default account THEN set default as 0`() =
        runTest {
            preferencesRepository.setDefaultHoYoLabAccountUid(123456789)
            useCase.deleteAccountFromDB(123456789)
            val result = preferencesRepository.getDefaultHoYoLabAccountUid().first()
            assertEquals(0, result)
        }
}
