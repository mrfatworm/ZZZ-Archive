/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

import feature.hoyolab.data.credential.HoYoLabCredential
import feature.hoyolab.data.credential.HoYoLabCredentialStore
import feature.hoyolab.data.crypto.ZzzCrypto
import feature.hoyolab.data.repository.HoYoLabConfigRepository
import feature.setting.data.PreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

/** [droppedAccounts] is how many accounts could not be carried over and had to be unlinked. */
data class HoYoLabCredentialMigrationResult(val droppedAccounts: Int)

/**
 * Moves the HoYoLab session cookies out of Room and into [HoYoLabCredentialStore], once, on the
 * first launch after the move.
 *
 * Until then they sat in Room encrypted under `ZzzConfig.AES_KEY`, a single constant compiled into
 * every copy of the app — and, in a build made from the public sources, a constant anybody can
 * read. [legacyCrypto] is kept only to read those rows; nothing writes through it any more.
 *
 * An account whose ciphertext will not decrypt is unlinked rather than carried forward: that is
 * what a build whose key differs from the one that wrote the row looks like, and there is nothing
 * left to recover. The user is asked to link it again.
 *
 * The pass is restartable: anything it throws leaves the migrated flag unset, and rows it already
 * moved are recognised by their blanked columns and skipped on the next launch.
 */
class HoYoLabCredentialMigrationUseCase(
    private val hoYoLabConfigRepository: HoYoLabConfigRepository,
    private val credentialStore: HoYoLabCredentialStore,
    private val legacyCrypto: ZzzCrypto,
    private val preferencesRepository: PreferencesRepository,
    private val hoYoLabManageUseCase: HoYoLabManageUseCase
) {
    suspend fun migrateIfNeeded(): HoYoLabCredentialMigrationResult {
        if (preferencesRepository.getHoYoLabCredentialsMigrated().first()) {
            return HoYoLabCredentialMigrationResult(droppedAccounts = 0)
        }
        val accounts = hoYoLabConfigRepository.getAllAccountsFromDB().firstOrNull().orEmpty()
        var droppedAccounts = 0
        accounts.forEach { account ->
            // Already blank means an earlier run moved this one and was then interrupted before it
            // could set the flag: the credential is in the store and Room holds nothing to read.
            // Without this the empty ciphertext would fail to decrypt and unlink a working account.
            if (account.lToken.isEmpty() || account.ltUid.isEmpty()) {
                return@forEach
            }
            val credential =
                runCatching {
                    HoYoLabCredential(
                        lToken = legacyCrypto.decryptData(account.lToken),
                        ltUid = legacyCrypto.decryptData(account.ltUid)
                    )
                }.getOrNull()
            if (credential == null) {
                // Goes through the manage use case so the default-account preference is reset
                // along with the row.
                hoYoLabManageUseCase.deleteAccountFromDB(account.uid)
                droppedAccounts++
            } else {
                credentialStore.save(account.uid, credential)
                hoYoLabConfigRepository.clearLegacyCredentialsInDB(account.uid)
            }
        }
        preferencesRepository.setHoYoLabCredentialsMigrated(true)
        return HoYoLabCredentialMigrationResult(droppedAccounts = droppedAccounts)
    }
}
