/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayOutputStream

actual fun ImageBitmap.encodeToPng(): ByteArray {
    val bitmap = asAndroidBitmap()
    // A hardware bitmap keeps its pixels on the GPU, where the encoder cannot read them.
    val software =
        if (bitmap.config == Bitmap.Config.HARDWARE) bitmap.copy(Bitmap.Config.ARGB_8888, false) else bitmap
    return ByteArrayOutputStream().use { stream ->
        software.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.toByteArray()
    }
}
