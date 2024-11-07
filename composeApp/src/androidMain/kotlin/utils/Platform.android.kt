/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import android.os.Build

class AndroidPlatform : Platform {
    override val operatingSystemName: String = "Android ${Build.VERSION.SDK_INT}"
    override val deviceName: String = Build.MODEL
}

actual fun getPlatform(): Platform = AndroidPlatform()