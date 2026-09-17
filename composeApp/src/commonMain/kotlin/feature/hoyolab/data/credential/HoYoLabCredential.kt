/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.credential

/**
 * The HoYoLab session cookie pair the user pasted by hand. Never persisted in Room — it lives in
 * [HoYoLabCredentialStore], behind the platform key store.
 */
data class HoYoLabCredential(val lToken: String, val ltUid: String)
