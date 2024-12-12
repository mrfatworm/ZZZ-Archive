/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.TypeConverter
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class ByteArrayConverter {

    @OptIn(ExperimentalEncodingApi::class)
    @TypeConverter
    fun fromByteArray(byteArray: ByteArray): String {
        return Base64.Default.encode(byteArray)
    }

    @OptIn(ExperimentalEncodingApi::class)
    @TypeConverter
    fun toByteArray(encodedString: String): ByteArray {
        return Base64.Default.decode(encodedString)
    }
}