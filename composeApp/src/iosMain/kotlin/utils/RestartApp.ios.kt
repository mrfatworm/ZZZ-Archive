/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package utils

import coil3.PlatformContext
import kotlin.system.exitProcess

actual fun restartApp(platformContext: PlatformContext) {
    exitProcess(0)
}