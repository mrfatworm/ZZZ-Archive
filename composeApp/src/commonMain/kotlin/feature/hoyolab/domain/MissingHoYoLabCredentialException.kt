/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.domain

/**
 * The account row survives but its session cookies are gone from the platform key store, so the
 * account can only be recovered by linking it again.
 */
class MissingHoYoLabCredentialException(uid: Int) : Exception("No HoYoLab credential stored for account $uid")
