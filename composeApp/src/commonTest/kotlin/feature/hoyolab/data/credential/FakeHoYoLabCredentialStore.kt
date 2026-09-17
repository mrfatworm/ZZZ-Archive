/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.credential

class FakeHoYoLabCredentialStore(
    initialCredentials: Map<Int, HoYoLabCredential> = mapOf(123456789 to STUB_CREDENTIAL, 1300051361 to STUB_CREDENTIAL)
) : HoYoLabCredentialStore {
    private val credentials = initialCredentials.toMutableMap()

    val storedUids: Set<Int> get() = credentials.keys

    override suspend fun save(
        uid: Int,
        credential: HoYoLabCredential
    ) {
        credentials[uid] = credential
    }

    override suspend fun read(uid: Int): HoYoLabCredential? = credentials[uid]

    override suspend fun delete(uid: Int) {
        credentials.remove(uid)
    }

    companion object {
        val STUB_CREDENTIAL = HoYoLabCredential(lToken = "fake_ltoken", ltUid = "fake_lt_uid")
    }
}
