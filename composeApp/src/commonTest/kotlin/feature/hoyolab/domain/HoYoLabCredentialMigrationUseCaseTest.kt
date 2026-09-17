/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.credential.FakeHoYoLabCredentialStore
import feature.hoyolab.data.crypto.FakeZzzCrypto
import feature.hoyolab.data.repository.FakeHoYoLabConfigRepository
import feature.setting.data.FakePreferenceRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

class HoYoLabCredentialMigrationUseCaseTest {
    private val hoYoLabConfigRepository = FakeHoYoLabConfigRepository()
    private val credentialStore = FakeHoYoLabCredentialStore(initialCredentials = emptyMap())
    private val legacyCrypto = FakeZzzCrypto()
    private val preferencesRepository = FakePreferenceRepository()
    private val useCase =
        HoYoLabCredentialMigrationUseCase(
            hoYoLabConfigRepository = hoYoLabConfigRepository,
            credentialStore = credentialStore,
            legacyCrypto = legacyCrypto,
            preferencesRepository = preferencesRepository,
            hoYoLabManageUseCase =
                HoYoLabManageUseCase(hoYoLabConfigRepository, credentialStore, preferencesRepository)
        )

    @Test
    fun `Move the stored credentials into the credential store`() = runTest {
        val result = useCase.migrateIfNeeded()

        assertEquals(0, result.droppedAccounts)
        assertNotNull(credentialStore.read(123456789))
        assertTrue(preferencesRepository.getHoYoLabCredentialsMigrated().first())
    }

    @Test
    fun `Blank the columns the credentials came out of`() = runTest {
        useCase.migrateIfNeeded()

        val account = hoYoLabConfigRepository.getAllAccountsFromDB().first().first()
        assertEquals(0, account.lToken.size)
        assertEquals(0, account.ltUid.size)
    }

    @Test
    fun `GIVEN the migration already ran WHEN migrating THEN do nothing`() = runTest {
        preferencesRepository.setHoYoLabCredentialsMigrated(true)

        val result = useCase.migrateIfNeeded()

        assertEquals(0, result.droppedAccounts)
        assertNull(credentialStore.read(123456789))
    }

    @Test
    fun `GIVEN an earlier run already moved the account WHEN migrating again THEN leave it alone`() = runTest {
        // What an interrupted pass leaves behind: columns blanked, flag never set.
        hoYoLabConfigRepository.clearLegacyCredentialsInDB(123456789)

        val result = useCase.migrateIfNeeded()

        assertEquals(0, result.droppedAccounts)
        assertEquals(1, hoYoLabConfigRepository.getAllAccountsFromDB().first().size)
    }

    @Test
    fun `GIVEN the stored credentials cannot be decrypted WHEN migrating THEN unlink the account`() = runTest {
        legacyCrypto.setError(true)

        val result = useCase.migrateIfNeeded()

        assertEquals(1, result.droppedAccounts)
        assertNull(credentialStore.read(123456789))
        assertEquals(emptyList(), hoYoLabConfigRepository.getAllAccountsFromDB().first())
        assertTrue(preferencesRepository.getHoYoLabCredentialsMigrated().first())
    }
}
