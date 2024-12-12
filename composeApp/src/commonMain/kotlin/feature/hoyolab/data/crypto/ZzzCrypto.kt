package feature.hoyolab.data.crypto

interface ZzzCrypto {
    val key: String

    suspend fun encryptData(text: String): ByteArray

    suspend fun decryptData(encryptedData: ByteArray): String
}