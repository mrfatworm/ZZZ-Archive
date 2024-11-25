/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.domain

import com.mrfatworm.zzzarchive.ZzzConfig
import utils.getPlatform

class AppInfoUseCase {
    fun getAppVersion(): String {
        return ZzzConfig.VERSION
    }

    fun getDeviceInfo(): String {
        return getPlatform().deviceName
    }

    fun getDeviceOs(): String {
        return getPlatform().operatingSystemName
    }
}