/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.crypto


class FakeZzzCrypto : ZzzCrypto {
    override val key: String = "FakeKey"

    override suspend fun encryptData(text: String): ByteArray {
        return ByteArray(11)
    }

    override suspend fun decryptData(encryptedData: ByteArray): String {
        return "decodedText"
    }
}