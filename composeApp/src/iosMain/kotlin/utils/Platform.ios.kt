/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val operatingSystemName: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val deviceName: String = UIDevice.currentDevice.name
}

actual fun getPlatform(): Platform = IOSPlatform()