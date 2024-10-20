/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package utils

import android.content.Intent
import coil3.PlatformContext

actual fun restartApp(platformContext: PlatformContext) {
    val context = platformContext.applicationContext
    val packageManager = context.packageManager
    val intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
    val componentName = intent.component!!
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    context.startActivity(mainIntent)
    Runtime.getRuntime().exit(0)
}