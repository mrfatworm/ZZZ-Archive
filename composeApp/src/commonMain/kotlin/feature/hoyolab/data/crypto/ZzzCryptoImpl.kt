/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.crypto

import com.mrfatworm.zzzarchive.ZzzConfig
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.algorithms.AES.Key
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
class ZzzCryptoImpl : ZzzCrypto {
    override val key = ZzzConfig.AES_KEY

    override suspend fun encryptData(text: String): ByteArray {
        val provider = CryptographyProvider.Default
        val aesCbc = provider.get(AES.CBC)
        val restoredKeyBytes = Base64.Default.decode(key)
        val restoredKey = aesCbc.keyDecoder().decodeFromByteArray(Key.Format.RAW, restoredKeyBytes)
        val cipher = restoredKey.cipher()
        return cipher.encrypt(text.encodeToByteArray())
    }

    override suspend fun decryptData(encryptedData: ByteArray): String {
        val provider = CryptographyProvider.Default
        val aesCbc = provider.get(AES.CBC)
        val restoredKeyBytes = Base64.Default.decode(key)
        val restoredKey = aesCbc.keyDecoder().decodeFromByteArray(Key.Format.RAW, restoredKeyBytes)
        val cipher = restoredKey.cipher()
        return cipher.decrypt(encryptedData).decodeToString()
    }
}