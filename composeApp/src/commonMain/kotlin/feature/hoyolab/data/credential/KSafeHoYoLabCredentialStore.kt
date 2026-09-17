/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.credential

import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.KSafeWriteMode

/**
 * Keeps the cookies in KSafe, whose AES-GCM key is held by the platform rather than compiled into
 * the app: Android Keystore, the iOS Keychain, and the host OS secret store on desktop. The desktop
 * key is still reachable by anything running as the same user — what it buys there is a key that
 * differs per machine instead of one shared by every install.
 */
class KSafeHoYoLabCredentialStore(private val kSafe: KSafe) : HoYoLabCredentialStore {
    override suspend fun save(
        uid: Int,
        credential: HoYoLabCredential
    ) {
        kSafe.put(lTokenKey(uid), credential.lToken, mode = KSafeWriteMode.Encrypted())
        kSafe.put(ltUidKey(uid), credential.ltUid, mode = KSafeWriteMode.Encrypted())
    }

    override suspend fun read(uid: Int): HoYoLabCredential? {
        val lToken = kSafe.get(lTokenKey(uid), NOT_STORED)
        val ltUid = kSafe.get(ltUidKey(uid), NOT_STORED)
        return if (lToken == NOT_STORED || ltUid == NOT_STORED) {
            null
        } else {
            HoYoLabCredential(lToken = lToken, ltUid = ltUid)
        }
    }

    override suspend fun delete(uid: Int) {
        kSafe.delete(lTokenKey(uid))
        kSafe.delete(ltUidKey(uid))
    }

    private fun lTokenKey(uid: Int) = "hoyolab_ltoken_$uid"

    private fun ltUidKey(uid: Int) = "hoyolab_ltuid_$uid"

    private companion object {
        // KSafe answers a missing key with the default handed to `get`, so the default doubles
        // as the "absent" marker. An empty cookie never reaches here: the add-account form
        // rejects blank fields.
        const val NOT_STORED = ""
    }
}
