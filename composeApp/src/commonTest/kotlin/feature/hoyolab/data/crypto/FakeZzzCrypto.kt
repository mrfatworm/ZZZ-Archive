/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.crypto

class FakeZzzCrypto : ZzzCrypto {
    override val key: String = "FakeKey"

    private var isError = false

    /** Stands in for a build whose `AES_KEY` differs from the one that wrote the stored rows. */
    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun encryptData(text: String): ByteArray = ByteArray(11)

    override suspend fun decryptData(encryptedData: ByteArray): String = when {
        isError -> throw IllegalStateException("Cannot decrypt")

        // Real AES-CBC has nothing to work with below one IV, so a blanked column throws rather
        // than decoding to something.
        encryptedData.isEmpty() -> throw IllegalArgumentException("Empty ciphertext")

        else -> "decodedText"
    }
}
