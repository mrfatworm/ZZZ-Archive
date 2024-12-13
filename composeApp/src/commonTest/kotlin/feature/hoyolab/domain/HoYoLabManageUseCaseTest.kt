/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.database.stubHoYoLabAccountEntity
import feature.hoyolab.data.repository.FakeHoYoLabRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HoYoLabManageUseCaseTest {
    private val repository = FakeHoYoLabRepository()
    private val zzzCrypto = FakeZzzCrypto()
    private val useCase = HoYoLabManageUseCase(repository, zzzCrypto)

    @Test
    fun `Request user game roles and save to database THEN success`() = runTest {
        val result = useCase.requestUserGameRolesAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
        assertEquals(result, Result.success(Unit))
        assertTrue(repository.isAdded)
    }

    @Test
    fun `GIVEN Accounts list is empty WHEN Request user game roles and save to database THEN success`() =
        runTest {
            repository.setIsEmptyAccountList(true)
            val result =
                useCase.requestUserGameRolesAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
                    .getOrNull()
            assertNull(result)
        }

    @Test
    fun `Request user game roles and save to database THEN fail`() = runTest {
        repository.setError(true)
        val result = useCase.requestUserGameRolesAndSave("prod_gf_jp", "fake_ltoken", "fake_lt_uid")
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
        assertTrue(repository.isDeleted)
    }
}