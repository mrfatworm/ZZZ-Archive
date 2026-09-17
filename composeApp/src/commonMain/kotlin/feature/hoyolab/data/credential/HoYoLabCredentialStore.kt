/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.credential

/**
 * Per-account storage for HoYoLab session cookies, keyed by the account uid that identifies the
 * matching row in `HoYoLabAccountEntity`.
 */
interface HoYoLabCredentialStore {
    suspend fun save(
        uid: Int,
        credential: HoYoLabCredential
    )

    /** `null` when nothing is stored for [uid], which means the account can no longer be synced. */
    suspend fun read(uid: Int): HoYoLabCredential?

    suspend fun delete(uid: Int)
}
