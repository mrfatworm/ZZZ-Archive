/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image

actual fun ImageBitmap.encodeToPng(): ByteArray {
    val image = Image.makeFromBitmap(asSkiaBitmap())
    return checkNotNull(image.encodeToData(EncodedImageFormat.PNG)) { "Skia could not encode the image" }.bytes
}
