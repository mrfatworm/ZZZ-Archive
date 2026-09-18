/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import androidx.compose.ui.graphics.ImageBitmap

/** PNG bytes of a captured bitmap: `Bitmap.compress` on Android, Skia's encoder on the other targets. */
expect fun ImageBitmap.encodeToPng(): ByteArray
